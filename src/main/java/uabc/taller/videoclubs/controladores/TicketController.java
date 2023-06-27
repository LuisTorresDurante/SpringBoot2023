package uabc.taller.videoclubs.controladores;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import uabc.taller.videoclubs.dto.TicketModel;
import uabc.taller.videoclubs.servicios.TicketService;

@Controller
@RequestMapping("tickets")
public class TicketController {
	@Autowired
	TicketService ticketService;
	
	private HashMap<String, Object> response(Boolean res, Object data, String message){
    	HashMap<String, Object> _response = new HashMap<>();
    	_response.put("response", res);
    	_response.put("message", message);
    	_response.put("data", data);
    	return _response;
    }
	
	@RequestMapping("")
	public String multas(Model model, HttpServletRequest request, HttpServletResponse response) {
		return "views/multa";
	}
	
	@GetMapping("obtener")
	@ResponseBody
	public HashMap<String, Object> obtenerTickets(HttpServletRequest request, HttpServletResponse response, Integer customerId) {
		List<TicketModel> tickets = ticketService.findFromCustomerCustom(customerId);
		
		return response(true, tickets, "");
	}
}
