package br.com.ekan.planosaude.controller;



import br.com.ekan.planosaude.service.BeneficiarioService;
import br.com.ekan.planosaude.util.MediaType;
import br.com.ekan.planosaude.vo.v1.BeneficiarioVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/beneficiario/")
@Tag(name = "Beneficiário", description = "Endpoints para gerenciamento de beneficiários")
public class BeneficiarioController {

    @Autowired
    BeneficiarioService beneficiarioService;

    private static final Logger log = LoggerFactory.getLogger(BeneficiarioController.class);

    @PostMapping(consumes = { MediaType.APPLICATION_JSON }, produces = { MediaType.APPLICATION_JSON })
    @Operation(summary = "Adiciona um novo beneficiário",
            description = "Adiciona um novo beneficiário passando uma representação JSON do beneficiário!",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BeneficiarioVO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public BeneficiarioVO creatBeneficiario(@RequestBody BeneficiarioVO beneficiario) {
        return beneficiarioService.create(beneficiario);
    }

    @PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON }, produces = { MediaType.APPLICATION_JSON  })
    @Operation(summary = "Altera um beneficiário",
            description = "Altera o beneficiário passando uma representação JSON do beneficiário!",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BeneficiarioVO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public BeneficiarioVO updateBeneficiario(@PathVariable("id") Long id, @RequestBody BeneficiarioVO beneficiario) {
        return beneficiarioService.update(id, beneficiario);
    }

    @GetMapping(value = "/listaBeneficiarios", consumes = { MediaType.APPLICATION_JSON }, produces = { MediaType.APPLICATION_JSON  })
    @Operation(summary = "Retorna todos beneficiários",
            description = "Retorna todos beneficiários!",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BeneficiarioVO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<List<BeneficiarioVO>> listarBeneficiarios() {
        log.info("Buscando todos beneficiários");
        return ResponseEntity.ok(beneficiarioService.listaBeneficiarios());
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deleta um beneficiário",
            description = "Deleta o beneficiário passando uma representação JSON do beneficiário!",
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<?>deleteBeneficiario(@PathVariable(value = "id") Long id) {
        beneficiarioService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
