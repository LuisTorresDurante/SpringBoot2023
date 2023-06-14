package uabc.taller.videoclubs.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import uabc.taller.videoclubs.entidades.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer> {

	Staff findByUsername(String username);
	
	
}
