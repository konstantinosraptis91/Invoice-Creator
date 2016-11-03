/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.controller;

import invoice.parser.entity.EntityList;
import invoice.parser.entity.Invoice;
import invoice.parser.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author konstantinos
 */
@RestController
@RequestMapping("/invoice")
public class InvoiceController {
       
    @Autowired
    private InvoiceService invoiceService;
    
    @RequestMapping(value="/{id}", method = RequestMethod.GET, 
            produces = MediaType.APPLICATION_XML_VALUE)
    public Invoice getInvoiceById(@PathVariable("id") int id) {
        return invoiceService.getInvoiceById(id);
    }
    
    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_XML_VALUE)
    public EntityList<Invoice> getInvoices() {
        return new EntityList<>(invoiceService.getInvoices());
    }
    
}