package uabc.taller.videoclubs.servicios;

import java.util.List;

import uabc.taller.videoclubs.dto.TicketModel;
import uabc.taller.videoclubs.entidades.Ticket;

public interface ITicketService {

	void save(Ticket ticket);

	List<TicketModel> findFromCustomerCustom(Integer customerId);
	

}
