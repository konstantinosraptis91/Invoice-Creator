package invoice.parser.service;

import invoice.parser.entity.Form.Supplier;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import invoice.parser.dao.SupplierDao;

/**
 *
 * @author konstantinos
 */
@Service
public class SupplierService {
    
    @Autowired
    @Qualifier("MySQLSupplier")
    private SupplierDao supplierDao;
    
    public int addSupplier(Supplier supplier) {
        return supplierDao.addSupplier(supplier);
    }
    
    public Supplier getSupplierById(int id) {
        return supplierDao.getSupplierById(id);
    }
    
    public List<Supplier> getSuppliers() {
        return supplierDao.getSuppliers();
    }
    
}
