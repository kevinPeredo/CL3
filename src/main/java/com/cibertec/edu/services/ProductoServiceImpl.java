package com.cibertec.edu.services;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.edu.models.Producto;
import com.cibertec.edu.repositories.ProductoDao;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service
public class ProductoServiceImpl  implements ProductoService{
	
	@Autowired
	private ProductoDao productoRepository;
	
	@Override
	public List<Producto> getAllProductos(){
		return this.productoRepository.findAll();
	}
	
	@Override
	public InputStream getReportProductos() throws Exception{
		try {
			Map<String,Object> parameters = new HashMap<>();
			List<String> nombre = Arrays.asList("Aceite primor 1 litro");
			parameters.put("NOMBRE_PATH", nombre );
			List<String> descripcion = Arrays.asList("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
			parameters.put("DESCRIPCION_PATH", descripcion);
			List<String> fechaRegistro = Arrays.asList("06/22/2023");
			parameters.put("FECHA_PATH", fechaRegistro);
			
		
			
			JasperReport jasperReportObj = getJasperReportCompiled();
			JasperPrint jPrint = JasperFillManager.fillReport(jasperReportObj, parameters);
			InputStream result = new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jPrint));
			return result;			
		} catch (JRException ex) {
			throw ex;
		}
	}
	
	private JasperReport getJasperReportCompiled() {
		try {
			InputStream productoReportStream = getClass().getResourceAsStream("/jasper/Reporte_Productos.jrxml");
			JasperReport jasper = JasperCompileManager.compileReport(productoReportStream);
			return jasper;
		} catch (Exception e) {
			return null;
		}
	}

}