package uabc.taller.videoclubs.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;


@Entity
@Table(name = "actor")
public class Actor {
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "actor_id")
	private Integer actorId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
}
