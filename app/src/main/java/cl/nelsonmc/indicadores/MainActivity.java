package cl.nelsonmc.indicadores;

import static cl.nelsonmc.indicadores.UIModeOption.MODO_CLARO;
import static cl.nelsonmc.indicadores.UIModeOption.MODO_OSCURO;
import static cl.nelsonmc.indicadores.UIModeOption.PREDETERMINADO;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Objects;

import cl.nelsonmc.indicadores.indicadores.IndicadorActivity;

public class MainActivity extends AppCompatActivity {

    private TextView fechaDolarText, valorDolarText;
    private TextView fechaEuroTextView, valorEuroText;
    private TextView fechaUFTextView, valorUFText;
    private TextView fechaIVPTextView, valorIVPText;
    private TextView fechaIPCTextView, valorIPCText;
    private TextView fechaUTMTextView, valorUTMText;
    private TextView fechaIMACECTextView, valorIMACECText;
    private TextView fechaCobreTextView, valorCobreText;
    private TextView fechaDesempleoText, valorDesempleoText;
    private TextView fechaBitcoinText, valorBitcoinText;
    private ImageView trendingDolar, trendingEuro, trendingUF;
    private ImageView trendingIVP, trendingIPC, trendingUTM, trendingIMACEC;
    private ImageView trendingCobre, trendingDesempleo, trendingBitcoin;
    private MainActivityModelView viewModel;
    private SwipeRefreshLayout swipeRefreshLayout;
    private BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpView();

        viewModel = new ViewModelProvider(this).get(MainActivityModelView.class);

        viewModel.loadData();

        viewModel.getDolarListObserver().observe(this, serieIndicador -> {
            if (serieIndicador != null) {
                fechaDolarText.setText(viewModel.dateUtcToString(serieIndicador.get(0).getFecha()));
                valorDolarText.setText(viewModel.decimalFormat(serieIndicador.get(0).getValor()));
                float valorHoy = Float.parseFloat(serieIndicador.get(0).getValor());
                float valorAyer = Float.parseFloat(serieIndicador.get(1).getValor());
                if (valorHoy < valorAyer) {
                    trendingDolar.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_trending_down));
                }
            }
        });

        viewModel.getEuroListObserver().observe(this, serieIndicador -> {
            if (serieIndicador != null) {
                fechaEuroTextView.setText(viewModel.dateUtcToString(serieIndicador.get(0).getFecha()));
                valorEuroText.setText(viewModel.decimalFormat(serieIndicador.get(0).getValor()));
                float valorHoy = Float.parseFloat(serieIndicador.get(0).getValor());
                float valorAyer = Float.parseFloat(serieIndicador.get(1).getValor());
                if (valorHoy < valorAyer) {
                    trendingEuro.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_trending_down));
                }
            }
            swipeRefreshLayout.setRefreshing(false);
        });

        viewModel.getUFListObserver().observe(this, serieIndicador -> {
            if (serieIndicador != null) {
                fechaUFTextView.setText(viewModel.dateUtcToString(serieIndicador.get(0).getFecha()));
                valorUFText.setText(viewModel.decimalFormat(serieIndicador.get(0).getValor()));
                float valorHoy = Float.parseFloat(serieIndicador.get(0).getValor());
                float valorAyer = Float.parseFloat(serieIndicador.get(1).getValor());
                if (valorHoy < valorAyer) {
                    trendingUF.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_trending_down));
                }
            }
            swipeRefreshLayout.setRefreshing(false);
        });

        viewModel.getIVPListObserver().observe(this, serieIndicador -> {
            if (serieIndicador != null) {
                fechaIVPTextView.setText(viewModel.dateUtcToString(serieIndicador.get(0).getFecha()));
                valorIVPText.setText(viewModel.decimalFormat(serieIndicador.get(0).getValor()));
                float valorHoy = Float.parseFloat(serieIndicador.get(0).getValor());
                float valorAyer = Float.parseFloat(serieIndicador.get(1).getValor());
                if (valorHoy < valorAyer) {
                    trendingIVP.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_trending_down));
                }
            }
            swipeRefreshLayout.setRefreshing(false);
        });

        viewModel.getIPCListObserver().observe(this, serieIndicador -> {
            if (serieIndicador != null) {
                fechaIPCTextView.setText(viewModel.dateUtcToString(serieIndicador.get(0).getFecha()));
                valorIPCText.setText(viewModel.decimalFormat(serieIndicador.get(0).getValor()));
                float valorHoy = Float.parseFloat(serieIndicador.get(0).getValor());
                float valorAyer = Float.parseFloat(serieIndicador.get(1).getValor());
                if (valorHoy < valorAyer) {
                    trendingIPC.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_trending_down));
                }
            }
            swipeRefreshLayout.setRefreshing(false);
        });

        viewModel.getUTMListObserver().observe(this, serieIndicador -> {
            if (serieIndicador != null) {
                fechaUTMTextView.setText(viewModel.dateUtcToString(serieIndicador.get(0).getFecha()));
                valorUTMText.setText(viewModel.decimalFormat(serieIndicador.get(0).getValor()));
                float valorHoy = Float.parseFloat(serieIndicador.get(0).getValor());
                float valorAyer = Float.parseFloat(serieIndicador.get(1).getValor());
                if (valorHoy < valorAyer) {
                    trendingUTM.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_trending_down));
                }
            }
            swipeRefreshLayout.setRefreshing(false);
        });

        viewModel.getIMACECListObserver().observe(this, serieIndicador -> {
            if (serieIndicador != null) {
                fechaIMACECTextView.setText(viewModel.dateUtcToString(serieIndicador.get(0).getFecha()));
                valorIMACECText.setText(viewModel.decimalFormat(serieIndicador.get(0).getValor()));
                float valorHoy = Float.parseFloat(serieIndicador.get(0).getValor());
                float valorAyer = Float.parseFloat(serieIndicador.get(1).getValor());
                if (valorHoy < valorAyer) {
                    trendingIMACEC.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_trending_down));
                }
            }
            swipeRefreshLayout.setRefreshing(false);
        });

        viewModel.getCobreListObserver().observe(this, serieIndicador -> {
            if (serieIndicador != null) {
                fechaCobreTextView.setText(viewModel.dateUtcToString(serieIndicador.get(0).getFecha()));
                valorCobreText.setText(viewModel.decimalFormat(serieIndicador.get(0).getValor()));
                float valorHoy = Float.parseFloat(serieIndicador.get(0).getValor());
                float valorAyer = Float.parseFloat(serieIndicador.get(1).getValor());
                if (valorHoy < valorAyer) {
                    trendingCobre.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_trending_down));
                }
            }
            swipeRefreshLayout.setRefreshing(false);
        });

        viewModel.getDesempleoListObserver().observe(this, serieIndicador -> {
            if (serieIndicador != null) {
                fechaDesempleoText.setText(viewModel.dateUtcToString(serieIndicador.get(0).getFecha()));
                valorDesempleoText.setText(viewModel.decimalFormat(serieIndicador.get(0).getValor()));
                float valorHoy = Float.parseFloat(serieIndicador.get(0).getValor());
                float valorAyer = Float.parseFloat(serieIndicador.get(1).getValor());
                if (valorHoy < valorAyer) {
                    trendingDesempleo.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_trending_down));
                }
            }
            swipeRefreshLayout.setRefreshing(false);
        });

        viewModel.getBitcoinListObserver().observe(this, serieIndicador -> {
            if (serieIndicador != null) {
                fechaBitcoinText.setText(viewModel.dateUtcToString(serieIndicador.get(0).getFecha()));
                valorBitcoinText.setText(viewModel.decimalFormat(serieIndicador.get(0).getValor()));
                float valorHoy = Float.parseFloat(serieIndicador.get(0).getValor());
                float valorAyer = Float.parseFloat(serieIndicador.get(1).getValor());
                if (valorHoy < valorAyer) {
                    trendingBitcoin.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_trending_down));
                }
            }
            swipeRefreshLayout.setRefreshing(false);
        });

        viewModel.actualizarValores();
    }

    private void setUpView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout coll_toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        coll_toolbar.setTitle("Indicadores");
        coll_toolbar.setCollapsedTitleTextAppearance(R.style.coll_toolbar_title);
        coll_toolbar.setExpandedTitleTextAppearance(R.style.exp_toolbar_title);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        fechaDolarText = findViewById(R.id.fechaDolarTextView);
        valorDolarText = findViewById(R.id.valorDolarTextView);
        fechaEuroTextView = findViewById(R.id.fechaEuroTextView);
        valorEuroText = findViewById(R.id.valorEuroTextView);
        fechaUFTextView = findViewById(R.id.fechaUFTextView);
        valorUFText = findViewById(R.id.valorUFTextView);
        fechaIVPTextView = findViewById(R.id.fechaIVPTextView);
        valorIVPText = findViewById(R.id.valorIVPTextView);
        fechaIPCTextView = findViewById(R.id.fechaIPCTextView);
        valorIPCText = findViewById(R.id.valorIPCTextView);
        fechaUTMTextView = findViewById(R.id.fechaUTMTextView);
        valorUTMText = findViewById(R.id.valorUTMTextView);
        fechaIMACECTextView = findViewById(R.id.fechaIMACECTextView);
        valorIMACECText = findViewById(R.id.valorIMACECTextView);
        fechaCobreTextView = findViewById(R.id.fechaCobreTextView);
        valorCobreText = findViewById(R.id.valorCobreTextView);
        fechaDesempleoText = findViewById(R.id.fechaDesempleoTextView);
        valorDesempleoText = findViewById(R.id.valorDesempleoTextView);
        fechaBitcoinText = findViewById(R.id.fechaBitcoinTextView);
        valorBitcoinText = findViewById(R.id.valorBitcoinTextView);
        trendingDolar = findViewById(R.id.trendingDolar);
        trendingEuro = findViewById(R.id.trendingEuro);
        trendingUF = findViewById(R.id.trendingUF);
        trendingIVP = findViewById(R.id.trendingIVP);
        trendingIPC = findViewById(R.id.trendingIPC);
        trendingUTM = findViewById(R.id.trendingUTM);
        trendingIMACEC = findViewById(R.id.trendingIMACEC);
        trendingCobre = findViewById(R.id.trendingCobre);
        trendingDesempleo = findViewById(R.id.trendingDesempleo);
        trendingBitcoin = findViewById(R.id.trendingBitcoin);

        MobileAds.initialize(this, initializationStatus -> {
        });
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        swipeRefreshLayout.setOnRefreshListener(() -> viewModel.actualizarValores());
    }

    public void goToIndicador(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        String json = sharedPreferences.getString((String) view.getTag(), null);
        if (json == null) {
            return;
        }
        Intent intent = new Intent(MainActivity.this, IndicadorActivity.class);
        intent.putExtra("tipoData", (String) view.getTag());
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.showBottomMenu) {
            bottomSheetDialog = new BottomSheetDialog(this, R.style.BottomSheetDialogTheme);
            bottomSheetDialog.setContentView(R.layout.layout_bottom_sheet);
            uiModeOptions();
            RadioGroup radioGroup = bottomSheetDialog.findViewById(R.id.radio_group);
            if (radioGroup != null) {
                radioGroup.setOnCheckedChangeListener((radioGroup1, checkedId) -> {
                    switch (checkedId) {
                        case R.id.rb_claro:
                            PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit().putString("option_mode", MODO_CLARO.toString()).apply();
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            bottomSheetDialog.dismiss();
                            break;

                        case R.id.rb_oscuro:
                            PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit().putString("option_mode", MODO_OSCURO.toString()).apply();
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            bottomSheetDialog.dismiss();
                            break;

                        case R.id.rb_sistema:
                            PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit().putString("option_mode", PREDETERMINADO.toString()).apply();
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                            bottomSheetDialog.dismiss();
                            break;
                    }
                });
            }
            bottomSheetDialog.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void uiModeOptions() {
        String modeOptionString = PreferenceManager.getDefaultSharedPreferences(this).getString("option_mode", PREDETERMINADO.toString());
        RadioButton rb_claro = bottomSheetDialog.findViewById(R.id.rb_claro);
        RadioButton rb_oscuro = bottomSheetDialog.findViewById(R.id.rb_oscuro);
        RadioButton rb_sistema = bottomSheetDialog.findViewById(R.id.rb_sistema);

        if (Build.VERSION.SDK_INT < 29) {
            rb_sistema.setVisibility(View.GONE);
        }
        UIModeOption modeOption = UIModeOption.valueOf(modeOptionString);
        switch (modeOption) {
            case MODO_OSCURO:
                rb_oscuro.setChecked(true);
                break;
            case MODO_CLARO:
                rb_claro.setChecked(true);
                break;
            case PREDETERMINADO:
                rb_sistema.setChecked(true);
        }
    }

}