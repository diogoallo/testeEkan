package br.com.ekan.planosaude.service;



import br.com.ekan.planosaude.converter.DozerConverter;
import br.com.ekan.planosaude.exception.ResourceNotFoundException;
import br.com.ekan.planosaude.model.Beneficiario;
import br.com.ekan.planosaude.repository.BeneficiarioRepository;
import br.com.ekan.planosaude.repository.DocumentoRepository;
import br.com.ekan.planosaude.util.Utils;
import br.com.ekan.planosaude.vo.v1.BeneficiarioVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BeneficiarioService {

    private static final Logger log = LoggerFactory.getLogger(BeneficiarioService.class);

    @Autowired
    private BeneficiarioRepository repository;

    @Autowired
    private DocumentoRepository documentoRepository;

    public BeneficiarioVO create(BeneficiarioVO beneficiarioVO)  {

        Optional<Beneficiario> beneficiario = this.buscarCpfBeneficiario(beneficiarioVO.getCpf());
        if(beneficiario.isPresent())
            throw new ResourceNotFoundException("Beneficiário já cadastrado");

        Date data = new Date(System.currentTimeMillis());
        beneficiarioVO.setDataInclusao(Utils.FormataData(data));
        beneficiarioVO.setDataAtualizacao(null);

        var entity = DozerConverter.parseObject(beneficiarioVO, Beneficiario.class);
        var vo = DozerConverter.parseObject(repository.save(entity), BeneficiarioVO.class);
        return vo;
    }

    public BeneficiarioVO update(Long id, BeneficiarioVO beneficiario) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Beneficiário não encontrado!"));

        Date data = new Date(System.currentTimeMillis());

        entity.setNome(beneficiario.getNome());
        entity.setCpf(beneficiario.getCpf());
        entity.setDataNascimento(beneficiario.getDataNascimento());
        entity.setDataAtualizacao(Utils.FormataData(data));

        var vo = DozerConverter.parseObject(repository.save(entity), BeneficiarioVO.class);
        return vo;
    }

    public void delete(Long id) {
        Beneficiario entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Beneficiário não encontrado!"));
        repository.delete(entity);
    }

    public List<BeneficiarioVO> listaBeneficiarios() {
        return DozerConverter.parseListObjects(repository.findAll(), BeneficiarioVO.class);
    }

    public Optional<Beneficiario> buscarCpfBeneficiario(String cpf) {
        log.info("Busca beneficiário pelo cpf {}", cpf);
        return Optional.ofNullable(this.repository.findByCpf(cpf));
    }

}
