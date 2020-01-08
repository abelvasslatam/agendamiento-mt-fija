package tdp.backend.mt.fija.main.mt.model;

import javax.persistence.*;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Table(name = "hfc_ps_list", schema = "public")
@Data
@ToString
public class HfcPsListMt implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_ps")
    private Long idPs;

    @Column(name = "des_ps")
    private String desPs;

    @Column(name="des_technology")
    private String desTechnology;

   
}
