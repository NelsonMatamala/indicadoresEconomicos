package cl.nelsonmc.indicadores.presentation.indicadores;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.List;

import cl.nelsonmc.indicadores.R;
import cl.nelsonmc.indicadores.common.Utils;

public class CustomMarkerView extends MarkerView {

    private final TextView tvValor;
    private final List<String> fechasList;
    private final Utils utils;

    public CustomMarkerView(Context context, List<String> fechasList) {
        super(context, R.layout.marker_view);
        this.fechasList = fechasList;
        this.utils = new Utils();
        tvValor = findViewById(R.id.tvMarkerValor);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        int index = (int) e.getX();
        String fecha = (index >= 0 && index < fechasList.size()) ? fechasList.get(index) : "";
        tvValor.setText(fecha + ": " + utils.decimalFormat(e.getY()));
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2f), -getHeight());
    }
}
