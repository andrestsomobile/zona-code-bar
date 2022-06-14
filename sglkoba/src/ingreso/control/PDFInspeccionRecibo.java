package ingreso.control;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import administracion.entity.empresa;
import ingreso.entity.contenedor_trafico;
import ingreso.entity.lote_contenedor_trafico;
import ingreso.entity.lote_trafico;
import ingreso.entity.trafico;
import maestro.control.gstcompania;
import maestro.control.gstproducto;
import maestro.control.gsttransportadora;
import maestro.entity.producto;
import util.Fecha;




public class PDFInspeccionRecibo {
	

	private Font fuenteBold_12 = new Font(Font.HELVETICA, 8, Font.BOLD, Color.BLACK);
	private Font fuenteNormal_12 = new Font(Font.HELVETICA, 7, Font.NORMAL, Color.BLACK);
	private Font fuenteNormal_10 = new Font(Font.HELVETICA, 5, Font.NORMAL, Color.BLACK);
	private Font fuenteBold_10 = new Font(Font.HELVETICA, 5, Font.BOLD, Color.BLACK);

	gsttrafico gtraf = new gsttrafico();
	gstcontenedor_trafico gcont = new gstcontenedor_trafico();
	gstlote_trafico glt = new gstlote_trafico();
	gstlote_contenedor_trafico glct = new gstlote_contenedor_trafico();


	gstcompania gcomp = new maestro.control.gstcompania();
	gsttransportadora gtransp = new maestro.control.gsttransportadora();
	gstproducto gprod = new maestro.control.gstproducto();
	
	public PDFInspeccionRecibo() {
	}
	
	
	public void pdf(String lctrafcodsx, String rutaarchivo, String codusu, String rutaContexto) {
		
		Document documento = new Document();
		String nomarch = rutaarchivo + File.separator + "InspeccionRecibo_" + lctrafcodsx + ".pdf";
		File f = new File(nomarch);
		f.delete();
		try {
			PdfWriter pw = PdfWriter.getInstance(documento, new FileOutputStream(nomarch));
			documento.open();
			documento.add(new Chunk(""));
			agregarContenido(documento, lctrafcodsx, codusu, rutaarchivo, rutaContexto);
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
			de.printStackTrace();
			System.out.print(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
			ioe.printStackTrace();
			System.out.print(ioe.getMessage());
		} finally {
			documento.close();
		}
	}
	

	private void agregarLineasEnBlanco(Paragraph parrafo, int nLineas) {
		for (int i = 0; i < nLineas; i++)
			parrafo.add(new Paragraph(" "));
	}

	private void agregarContenido(Document documento, String lctrafcodsx, String codusu, String rutaarchivo, String rutaContexto)
			throws DocumentException {
		
		empresa emp = new administracion.control.gstempresa().getempresa("4");


		lote_contenedor_trafico lct = glct.getlote_contenedor_trafico(lctrafcodsx);
		lote_trafico lt = glt.getlote_trafico(lct.getlctraflote());
		contenedor_trafico ct = gcont.getcontenedor_trafico(lct.getlctrafcontenedor());
		trafico traf = gtraf.gettrafico(ct.getctrafnumtrafico());
		producto pro = gprod.getproducto(lt.getltrafcodproducto());

		Paragraph ParrafoHoja = new Paragraph();
		PdfPCell cell = new PdfPCell();

		// Agregamos una linea en blanco
		agregarLineasEnBlanco(ParrafoHoja, 1);

		// Encabezado
		PdfPTable tabla = new PdfPTable(3); // Numero de columnas
		tabla.setWidthPercentage(100); // Porcentaje de la pagina que ocupa
		tabla.setHorizontalAlignment(Element.ALIGN_JUSTIFIED_ALL);// Alineacion horizontal

		try {
			String ruta = rutaContexto + File.separator + "disenno" + File.separator + "images" + File.separator + "logo.gif";
            Image img = Image.getInstance(ruta);
            img.scaleAbsolute(50,25);
            cell = new PdfPCell(img,false);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tabla.addCell(cell);
        } catch (Exception e) {
            cell = new PdfPCell();
            cell.addElement(new Paragraph(""));
            tabla.addCell(cell);
            System.out.print(e.getMessage());
        }

		cell = new PdfPCell();
		Paragraph p1 = new Paragraph("INSPECCION DE RECIBO", fuenteBold_10);
		p1.setAlignment(Element.ALIGN_CENTER);
		cell.addElement(p1);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    tabla.addCell(cell);
		 
		cell = new PdfPCell();
		Paragraph p2 = new Paragraph(emp.getempnombre() + "\n" + emp.getempdireccion() + "\n" + "NIT:" + emp.getempnit() + "\n"
				+ "FECHA DE IMPRESION:" + Fecha.getFechaSinHora(), fuenteBold_10);
		p2.setAlignment(Element.ALIGN_LEFT);
		cell.addElement(p2);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		tabla.addCell(cell);

		documento.add(ParrafoHoja);
		documento.add(tabla);

		ParrafoHoja = new Paragraph();
		agregarLineasEnBlanco(ParrafoHoja, 1);

		// Columnas encabezado

		tabla = new PdfPTable(4); // Numero de columnas
		tabla.setWidthPercentage(100); // Porcentaje de la pagina que ocupa
		tabla.setHorizontalAlignment(Element.ALIGN_CENTER);// Alineacion horizontal

		cell = celdaDato("Muelle", fuenteBold_10, Element.ALIGN_CENTER);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(1);	
		tabla.addCell(cell);
		
		cell = celdaDato("#", fuenteBold_10, Element.ALIGN_CENTER);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(3);	
		tabla.addCell(cell);
		
		
		documento.add(tabla);
			
			

	    
	   
		//Firmas
		
//	    tabla = new PdfPTable(7); // Numero de columnas
		tabla.setWidthPercentage(100); // Porcentaje de la pagina que ocupa
		tabla.setHorizontalAlignment(Element.ALIGN_CENTER);// Alineacion horizontal
		
		cell = celdaDato("\n" + "\n" + "\n" + "\n" + "\n", fuenteNormal_10, Element.ALIGN_LEFT);
		cell.setColspan(7);
		cell.setBorder(Rectangle.NO_BORDER);
		tabla.addCell(cell);
		
		
		cell = new PdfPCell();
		cell.setColspan(3);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph("_____________________________"));
		tabla.addCell(cell);
		
		cell = new PdfPCell();
		cell.setColspan(1);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph(""));
		tabla.addCell(cell);
		
		cell = new PdfPCell();
		cell.setColspan(3);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph("_____________________________"));
		tabla.addCell(cell);
		
		cell = celdaDato("RECIBIDO POR",fuenteNormal_12,Element.ALIGN_LEFT);
		cell.setColspan(3);
		cell.setBorder(Rectangle.NO_BORDER);
		tabla.addCell(cell);
		
		cell = new PdfPCell();
		cell.setColspan(1);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph(""));
		tabla.addCell(cell);
		
		cell = celdaDato("CONDUCTOR",fuenteNormal_12,Element.ALIGN_LEFT);
		cell.setColspan(3);
		cell.setBorder(Rectangle.NO_BORDER);
		tabla.addCell(cell);
		
		cell = celdaDato(ct.getCtrafconductor(),fuenteNormal_12,Element.ALIGN_LEFT);
		cell.setColspan(3);
		cell.setBorder(Rectangle.NO_BORDER);
		tabla.addCell(cell);
		
		cell = new PdfPCell();
		cell.setColspan(1);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph(""));
		tabla.addCell(cell);
		
		cell =  celdaDato(ct.getCtrafconductor(),fuenteNormal_12,Element.ALIGN_LEFT);
		cell.setColspan(3);
		cell.setBorder(Rectangle.NO_BORDER);
		tabla.addCell(cell);
		
		cell = celdaDato("Valido como Firma",fuenteNormal_12,Element.ALIGN_LEFT);
		cell.setColspan(3);
		cell.setBorder(Rectangle.NO_BORDER);
		tabla.addCell(cell);
		
		cell = new PdfPCell();
		cell.setColspan(1);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph(""));
		tabla.addCell(cell);
		
		cell = celdaDato("Valido como Firma",fuenteNormal_12,Element.ALIGN_LEFT);
		cell.setColspan(3);
		cell.setBorder(Rectangle.NO_BORDER);
		tabla.addCell(cell);

		documento.add(tabla);
		
		
	}
	
	
	
	PdfPCell celdaTitulo(String dato, Font fuente) {
		PdfPCell cell = new PdfPCell();
		Paragraph p = new Paragraph(dato, fuente);
		p.setAlignment(Element.ALIGN_CENTER);
		cell.addElement(p);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(Color.LIGHT_GRAY);
		return cell;
	}
	
	
	PdfPCell celdaSubTitulo(String dato, Font fuente) {
		PdfPCell cell = new PdfPCell();
		Paragraph p = new Paragraph(dato, fuente);
		p.setAlignment(Element.ALIGN_LEFT);
		cell.addElement(p);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(Color.LIGHT_GRAY);
		return cell;
	}
	
	PdfPCell celdaDato(String dato, Font fuente, int alineacion ) {
		PdfPCell cell = new PdfPCell();
		Paragraph p = new Paragraph(dato, fuente);
		p.setAlignment(alineacion);
		cell.addElement(p);
		cell.setHorizontalAlignment(alineacion);
		return cell;
	}
	
	
	    
	
}
