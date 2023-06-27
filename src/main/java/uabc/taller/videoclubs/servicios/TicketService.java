package uabc.taller.videoclubs.servicios;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uabc.taller.videoclubs.dto.TicketModel;
import uabc.taller.videoclubs.entidades.Ticket;
import uabc.taller.videoclubs.repositorios.CustomerRepository;
import uabc.taller.videoclubs.repositorios.TicketRepository;

@Service
public class TicketService implements ITicketService{

	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	
	@Override
	public void save(Ticket ticket) {
		ticketRepository.save(ticket);
	}
	
	
	public List<TicketModel> findFromCustomerCustom(Integer customerId){
		List<TicketModel> multas = new ArrayList<>();
		List<Ticket> tickets = ticketRepository.findFromCustomer(customerId);
		tickets.forEach(t->{
			TicketModel multa = new TicketModel(
					t.getTicketId(), 
					customerId, 
					t.getCustomer().getFirstName(), 
					t.getCustomer().getLastName(), 
					t.getRental().getRentalId(), 
					t.getRental().getRentalDate(), 
					t.getRental().getReturnDate(), 
					t.getAmount(), t.getActive(), 
					t.getRental().getInventory().getFilm().getTitle());
			multas.add(multa);
		});
		return multas;
	}
}
