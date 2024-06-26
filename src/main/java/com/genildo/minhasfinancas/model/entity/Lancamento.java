package com.genildo.minhasfinancas.model.entity;

import com.genildo.minhasfinancas.model.enums.StatusLancamento;
import com.genildo.minhasfinancas.model.enums.TipoLancamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table (name = "lancamento", schema = "financas")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Lancamento {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column ( name = "id")
    private Long id;

    @Column ( name = "descricao")
    private String descricao;

    @Column ( name = "mes")
    private Integer mes;

    @Column ( name = "ano")
    private Integer ano;

    @Column ( name = "valor")
    private BigDecimal valor;

    @Column ( name = "tipo")
    @Enumerated (value = EnumType.STRING )
    private TipoLancamento tipo;

    @Column ( name = "status")
    @Enumerated (value = EnumType.STRING)
    private StatusLancamento status;

    @ManyToOne
    @JoinColumn ( name = "id_usuario")
    private Usuario usuario;

    @Column (name = "data_cadastro")
    @Convert ( converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate dataCadastro;

    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDate.now();
    }
}
