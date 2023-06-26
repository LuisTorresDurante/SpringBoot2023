package uabc.taller.videoclubs.servicios.pdf;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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

import uabc.taller.videoclubs.dto.FilmDetails;


public class FilmPDFExporter {
	private final FilmDetails film;

    public FilmPDFExporter(FilmDetails film) {
        this.film = film;
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
        
        Paragraph Film = new Paragraph("Film #" + film.getFilmId(), fontTitle );
        Film.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(Film);
        
        document.add(new Paragraph("Titulo: ", fontBodyBold));
        document.add(new Paragraph(film.getTitle(), fontBody));
        
        document.add(Chunk.NEWLINE);
        
        document.add(new Paragraph("Descripcion", fontBodyBold));
        document.add(new Paragraph(film.getDescription(), fontBody));
        
        document.add(Chunk.NEWLINE);
        
        
        
        
        
        document.close();
    }

}
