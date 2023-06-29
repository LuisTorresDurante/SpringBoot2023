package uabc.taller.videoclubs.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "country")
public class Country {
    //Declaracion de variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "country")
    private String countryName;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "last_update")
    private java.sql.Timestamp lastUpdate;

}
