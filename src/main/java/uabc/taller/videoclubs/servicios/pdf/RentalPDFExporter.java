package uabc.taller.videoclubs.servicios.pdf;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

import uabc.taller.videoclubs.dto.RentalDTO;
import uabc.taller.videoclubs.util.QRCode;


public class RentalPDFExporter {
    private final RentalDTO rental;

    public RentalPDFExporter(RentalDTO rental) {
        this.rental = rental;
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(new Rectangle(216, 500), 5, 5, 10, 10);
        PdfWriter instance = PdfWriter.getInstance(document, response.getOutputStream());

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.COURIER, 14);

        Font fontBodyBold = FontFactory.getFont(FontFactory.COURIER_BOLD, 12);
        Font fontBody = FontFactory.getFont(FontFactory.COURIER, 12);

        DottedLineSeparator separator = new DottedLineSeparator();
        separator.setPercentage(59500f / 523f);
        Chunk linebreak = new Chunk(separator);

        InputStream template = getClass().getClassLoader()
                .getResourceAsStream("static/assets/imgs/sakila-logo1.jpg");

        if (template != null) {
            Image logo = Image.getInstance(template.readAllBytes());
            logo.scaleAbsolute(75, 75);
            logo.setAlignment(Element.ALIGN_CENTER);
            document.add(logo);
        }

        Paragraph pTitle = new Paragraph("Sakila Video Club", fontTitle);
        pTitle.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(pTitle);

        document.add(linebreak);
        
        Paragraph renta = new Paragraph("Renta #" + rental.getRentalId(), fontTitle );
        renta.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(renta);
        
        document.add(new Paragraph("Fecha de renta: ", fontTitle));
        document.add(new Paragraph(dateFormat.format(new Date(rental.getRentalDate().getTime())), fontBody));
        document.add(Chunk.NEWLINE);
        
        document.add(new Paragraph("Pelicula rentada: ", fontTitle));
        document.add(new Paragraph(rental.getTituloPelicula(), fontBody));
        document.add(Chunk.NEWLINE);
        
        document.add(new Paragraph("Nombre del cliente: ", fontTitle));
    	document.add(new Paragraph(rental.getCustomerId() + " " + rental.getNombreCliente(), fontBody));
    	document.add(Chunk.NEWLINE);
    	

        
        if(rental.getReturnDate() != null) {
        	
        document.add(new Paragraph("Fecha de regreso: ", fontBodyBold));
        document.add(new Paragraph(dateFormat.format(new Date(rental.getReturnDate().getTime())), fontBody));
        
        document.add(Chunk.NEWLINE);
        	
        }else {
        	document.add(new Paragraph("Esta pelicula NO se ha devuelto!", fontBodyBold));
        	document.add(Chunk.NEWLINE);
        }
        
        document.add(Chunk.NEWLINE);
        
        Paragraph pReturnDateLabel = new Paragraph("Ultima actualizacion: ", fontBodyBold);
        pReturnDateLabel.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(pReturnDateLabel);
        
        Paragraph pReturnDate = new Paragraph(dateFormat.format(rental.getLastUpdate().getTime()), fontBody);
        pReturnDate.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(pReturnDate);
        
        
        document.add(Chunk.NEWLINE);

        document.close();

    }
}