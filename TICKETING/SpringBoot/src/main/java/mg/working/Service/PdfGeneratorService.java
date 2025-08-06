package mg.working.Service;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import mg.working.model.Reservation;
import mg.working.model.ReservationMere;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfGeneratorService {

    private final SpringTemplateEngine templateEngine;

    public PdfGeneratorService(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public byte[] generatePdf(ReservationMere reservationMere) throws Exception {
        Context context = new Context();
        ByteArrayOutputStream outputStream  = new ByteArrayOutputStream();
        PdfRendererBuilder builder = new PdfRendererBuilder();
        List<ReservationMere> reservationMeres = new ArrayList<>();
        reservationMeres.add(reservationMere);
        context.setVariable("listreservationmere",reservationMeres);
        String htmlcontent = templateEngine.process("Liste", context);
        builder.useFastMode();
        builder.withHtmlContent(htmlcontent,null);
        builder.toStream(outputStream);
        builder.run();
        return outputStream.toByteArray();
    }

}
