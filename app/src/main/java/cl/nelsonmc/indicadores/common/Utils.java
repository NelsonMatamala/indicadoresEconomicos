package cl.nelsonmc.indicadores.common;

import org.threeten.bp.Instant;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.text.NumberFormat;
import java.util.Locale;

public class Utils {

    public String decimalFormat(String valor) {
        float numero = Float.parseFloat(valor);
        Locale chileLocale = new Locale("es", "CL");
        NumberFormat nf = NumberFormat.getNumberInstance(chileLocale);
        return nf.format(numero);
    }

    public String decimalFormat(Float valor) {
        Locale chileLocale = new Locale("es", "CL");
        NumberFormat nf = NumberFormat.getNumberInstance(chileLocale);
        return nf.format(valor);
    }

    public String dateUtcToString(String fecha) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Instant instantFecha = Instant.parse(fecha);
        return ZonedDateTime.ofInstant(instantFecha, ZoneId.of("America/Santiago")).format(dtf);
    }

}
