package br.com.ekan.planosaude.repository;



import br.com.ekan.planosaude.model.Beneficiario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long> {
    Beneficiario findByCpf(String cpf);

    @Query(value = "select * from beneficiario  where id = :beneficiarioId",
            nativeQuery = true)
    Beneficiario findByBeneficiarioId(Long beneficiarioId);

}
