package br.com.ekan.planosaude.controller;


import br.com.ekan.planosaude.exception.ResourceNotFoundException;
import br.com.ekan.planosaude.response.Response;
import br.com.ekan.planosaude.service.DocumentoService;
import br.com.ekan.planosaude.util.MediaType;
import br.com.ekan.planosaude.vo.v1.BeneficiarioVO;
import br.com.ekan.planosaude.vo.v1.DocumentoVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/documento/")
@Tag(name = "Documentos", description = "Endpoints para gerenciamento de documento do beneficiário")
public class DocumentoController {

    @Autowired
    DocumentoService documentoService;

    @PostMapping(consumes = { MediaType.APPLICATION_JSON }, produces = { MediaType.APPLICATION_JSON  }  )
    @Operation(summary = "Adiciona um novo documento para o beneficiário",
            description = "Adiciona um novo documento para beneficiário passando uma representação JSON!",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = DocumentoVO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public DocumentoVO createDocumento(@RequestBody DocumentoVO documento) {
        return documentoService.create(documento);
    }

    @PutMapping(value = "/{id}" ,consumes = { MediaType.APPLICATION_JSON }, produces = { MediaType.APPLICATION_JSON  })
    @Operation(summary = "Altera documento para o beneficiário",
            description = "Altera  documento para o beneficiário passando uma representação JSON!",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = DocumentoVO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public DocumentoVO updateDocumento(@PathVariable("id") Long id, @RequestBody DocumentoVO documento) {
        return documentoService.update(id, documento);
    }

    @GetMapping(value = "/listaDocumentos/{beneficiarioId}" ,
            produces = { "application/json", "application/xml", "application/x-yaml" })
    @Operation(summary = "Lista todos os documentos do beneficiário",
            description = "Lista todos os documentos do beneficiário!",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = DocumentoVO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<List<DocumentoVO>>listarBeneficiarios(@PathVariable("beneficiarioId") Long beneficiarioId) {
        return ResponseEntity.ok(documentoService.listaDocumentosBeneficiario(beneficiarioId));
    }
}
