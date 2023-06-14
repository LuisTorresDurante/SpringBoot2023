package uabc.taller.videoclubs.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "language")
public class Language {
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "language_id")
	private Integer languageId;
	
	@Column(name = "name")
	private Integer name;
	
	@UpdateTimestamp
	@Column(name = "last_update")
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private java.sql.Timestamp lastUpdate;
}
