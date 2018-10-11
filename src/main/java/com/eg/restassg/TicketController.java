package com.eg.restassg;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;




@Controller
public class TicketController {
	
	private static final Logger logger = LoggerFactory.getLogger(TicketController.class);
	Map<Integer, Ticket> tkData = new HashMap<Integer, Ticket>();
	@RequestMapping(value = TikRestURIConstants.DUMMY_TIK, method = RequestMethod.GET)
	public @ResponseBody Ticket getDummyTicket() {
		logger.info("Start getDummyTicket");
		Ticket tk = new Ticket();
		tk.setId(01);
		tk.setName("Hai");
		tk.setCreatedDate(new Date());
		tkData.put(01, tk);
		return tk;
	}
	@RequestMapping(value = TikRestURIConstants.GET_TIK, method = RequestMethod.GET)
	public @ResponseBody Ticket getTicket(@PathVariable("id") int tkId) {
		logger.info("Start getTicket. ID="+tkId);
		
		return tkData.get(tkId);
	}
	@RequestMapping(value = TikRestURIConstants.GET_ALL_TIK, method = RequestMethod.GET)
	public @ResponseBody List<Ticket> getAllTickets() {
		logger.info("Start getAllTickets.");
		List<Ticket> tks = new ArrayList<Ticket>();
		Set<Integer> tkIdKeys = tkData.keySet();
		for(Integer i : tkIdKeys){
			tks.add(tkData.get(i));
		}
		return tks;
	}
	@RequestMapping(value = TikRestURIConstants.CREATE_TIK, method = RequestMethod.POST)
	public @ResponseBody Ticket createTicket(@RequestBody Ticket tk) {
		logger.info("Start createTicket.");
		tk.setCreatedDate(new Date());
		tkData.put(tk.getId(), tk);
		return tk;
	}
	@RequestMapping(value = TikRestURIConstants.DELETE_TIK, method = RequestMethod.PUT)
	public @ResponseBody Ticket deleteEmployee(@PathVariable("id") int tkId) {
		logger.info("Start deleteEmployee.");
		Ticket tk = tkData.get(tkId);
		tkData.remove(tkId);
		return tk;
	}
	
}
