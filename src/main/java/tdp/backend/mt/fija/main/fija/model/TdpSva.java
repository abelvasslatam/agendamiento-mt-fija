package tdp.backend.mt.fija.main.fija.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tdp_sva", schema = "ibmx_a07e6d02edaf552")
@Data
public class TdpSva {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "prodtypecode")
    private String prodTypeCode;
    @Column(name = "code")
    private String code;
    @Column(name = "description")
    private String description;
    @Column(name = "unit")
    private String unit;
    @Column(name = "cost")
    private BigDecimal cost;
    @Column(name = "productcode")
    private String productCode;
    @Column(name = "sva_codigo")
    private String sva_Codigo;
}
