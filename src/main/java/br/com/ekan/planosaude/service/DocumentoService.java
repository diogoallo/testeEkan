package br.com.ekan.planosaude.service;


import br.com.ekan.planosaude.converter.DozerConverter;
import br.com.ekan.planosaude.exception.ResourceNotFoundException;
import br.com.ekan.planosaude.model.Beneficiario;
import br.com.ekan.planosaude.model.Documento;
import br.com.ekan.planosaude.repository.BeneficiarioRepository;
import br.com.ekan.planosaude.repository.DocumentoRepository;
import br.com.ekan.planosaude.util.Utils;

import br.com.ekan.planosaude.vo.v1.DocumentoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository repository;

    @Autowired
    private BeneficiarioRepository beneficiarioRepository;

    private static final Logger log = LoggerFactory.getLogger(BeneficiarioService.class);

    public DocumentoVO create(DocumentoVO documento) {

        String tipoDocumento = documento.getTipoDocumento().contains("RG") ? "0" : "1";
        Optional<Beneficiario> beneficiario = buscarBeneficiario(documento.getBeneficiarioId());

        if (!beneficiario.isPresent())
            throw new ResourceNotFoundException("Beneficiário " + documento.getBeneficiarioId() + " não encontrado!");

        if(buscarDoumentoBeneficiario((documento.getBeneficiarioId()),
                tipoDocumento).isPresent())
            throw new ResourceNotFoundException("Documento do Beneficiário já cadastrado");

        Date data = new Date(System.currentTimeMillis());

        documento.setBeneficiarioId(documento.getBeneficiarioId());
        documento.setDataInclusao(Utils.FormataData(data));
        documento.setDataAtualizacao(null);

        var entity = DozerConverter.parseObject(documento, Documento.class);
        var vo = DozerConverter.parseObject(repository.save(entity), DocumentoVO.class);
        return vo;
    }

    public DocumentoVO update(Long id, DocumentoVO documento) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Documento não encontrado!"));

        Date data = new Date(System.currentTimeMillis());

        entity.setDescricao(documento.getDescricao());
        entity.setDataAtualizacao(Utils.FormataData(data));
        var vo = DozerConverter.parseObject(repository.save(entity), DocumentoVO.class);
        return vo;
    }

    public List<DocumentoVO> listaDocumentosBeneficiario(Long beneficiarioId) {
        log.info("Busca documentos do beneficiário :" + beneficiarioId);
        if(!buscarBeneficiario((beneficiarioId)).isPresent())
            throw new ResourceNotFoundException("Beneficiário não cadastrado!");
        return DozerConverter.parseListObjects(repository.findByBeneficiarioId(beneficiarioId), DocumentoVO.class);
    }

    public Optional<Documento> buscarDoumentoBeneficiario(Long beneficiarioId, String tipoDocumento) {
        log.info("Busca documeto beneficiario  {}", beneficiarioId);
        return Optional.ofNullable(this.repository.findByDocumentoBeneficiario(beneficiarioId, tipoDocumento));
    }

    public Optional<Beneficiario> buscarBeneficiario(Long beneficiarioId) {
        log.info("Busca  beneficiario  {}", beneficiarioId);
        return Optional.ofNullable(this.beneficiarioRepository.findByBeneficiarioId(beneficiarioId));
    }

}
