package cl.nelsonmc.indicadores;

import static cl.nelsonmc.indicadores.BaseApplication.sharedPreferences;
import static cl.nelsonmc.indicadores.common.Constants.DARKMODE;
import static cl.nelsonmc.indicadores.common.Constants.LIGHTMODE;
import static cl.nelsonmc.indicadores.common.Constants.MODE_UI;
import static cl.nelsonmc.indicadores.common.Constants.PREDETERMINADO;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Objects;


import cl.nelsonmc.indicadores.databinding.ActivityMainBinding;
import cl.nelsonmc.indicadores.indicadores.IndicadorActivity;

public class MainActivity extends AppCompatActivity {
    private MainActivityModelView viewModel;
    private BottomSheetDialog bottomSheetDialog;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setUpView();

        viewModel = new ViewModelProvider(this).get(MainActivityModelView.class);

        viewModel.getDolarListObserver().observe(this, serieIndicador -> {
            if (serieIndicador != null) {
                binding.nScroll.fechaDolarTextView.setText(viewModel.dateUtcToString(serieIndicador.get(0).getFecha()));
                binding.nScroll.valorDolarTextView.setText(viewModel.decimalFormat(serieIndicador.get(0).getValor()));
                if(viewModel.checkValueDecreased(serieIndicador.get(0).getValor(),serieIndicador.get(1).getValor())){
                    binding.nScroll.trendingDolar.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_trending_down));}
            }
            binding.swipeRefresh.setRefreshing(false);
        });

        viewModel.getEuroListObserver().observe(this, serieIndicador -> {
            if (serieIndicador != null) {
                binding.nScroll.fechaEuroTextView.setText(viewModel.dateUtcToString(serieIndicador.get(0).getFecha()));
                binding.nScroll.valorEuroTextView.setText(viewModel.decimalFormat(serieIndicador.get(0).getValor()));
                if(viewModel.checkValueDecreased(serieIndicador.get(0).getValor(),serieIndicador.get(1).getValor())){
                    binding.nScroll.trendingEuro.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_trending_down));}
            }
            binding.swipeRefresh.setRefreshing(false);
        });

        viewModel.getUFListObserver().observe(this, serieIndicador -> {
            if (serieIndicador != null) {
                binding.nScroll.fechaUFTextView.setText(viewModel.dateUtcToString(serieIndicador.get(0).getFecha()));
                binding.nScroll.valorUFTextView.setText(viewModel.decimalFormat(serieIndicador.get(0).getValor()));
                if(viewModel.checkValueDecreased(serieIndicador.get(0).getValor(),serieIndicador.get(1).getValor())){
                    binding.nScroll.trendingUF.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_trending_down));
                }
            }
            binding.swipeRefresh.setRefreshing(false);
        });

        viewModel.getIVPListObserver().observe(this, serieIndicador -> {
            if (serieIndicador != null) {
                binding.nScroll.fechaIVPTextView.setText(viewModel.dateUtcToString(serieIndicador.get(0).getFecha()));
                binding.nScroll.valorIVPTextView.setText(viewModel.decimalFormat(serieIndicador.get(0).getValor()));
                if(viewModel.checkValueDecreased(serieIndicador.get(0).getValor(),serieIndicador.get(1).getValor())){
                    binding.nScroll.trendingIVP.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_trending_down));
                }
            }
            binding.swipeRefresh.setRefreshing(false);
        });

        viewModel.getIPCListObserver().observe(this, serieIndicador -> {
            if (serieIndicador != null) {
                binding.nScroll.fechaIPCTextView.setText(viewModel.dateUtcToString(serieIndicador.get(0).getFecha()));
                binding.nScroll.valorIPCTextView.setText(viewModel.decimalFormat(serieIndicador.get(0).getValor()));
                if(viewModel.checkValueDecreased(serieIndicador.get(0).getValor(),serieIndicador.get(1).getValor())){
                    binding.nScroll.trendingIPC.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_trending_down));
                }
            }
            binding.swipeRefresh.setRefreshing(false);
        });

        viewModel.getUTMListObserver().observe(this, serieIndicador -> {
            if (serieIndicador != null) {
                binding.nScroll.fechaUTMTextView.setText(viewModel.dateUtcToString(serieIndicador.get(0).getFecha()));
                binding.nScroll.valorUTMTextView.setText(viewModel.decimalFormat(serieIndicador.get(0).getValor()));
                if(viewModel.checkValueDecreased(serieIndicador.get(0).getValor(),serieIndicador.get(1).getValor())){
                    binding.nScroll.trendingUTM.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_trending_down));
                }
            }
            binding.swipeRefresh.setRefreshing(false);
        });

        viewModel.getIMACECListObserver().observe(this, serieIndicador -> {
            if (serieIndicador != null) {
                binding.nScroll.fechaIMACECTextView.setText(viewModel.dateUtcToString(serieIndicador.get(0).getFecha()));
                binding.nScroll.valorIMACECTextView.setText(viewModel.decimalFormat(serieIndicador.get(0).getValor()));
                if(viewModel.checkValueDecreased(serieIndicador.get(0).getValor(),serieIndicador.get(1).getValor())){
                    binding.nScroll.trendingIMACEC.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_trending_down));
                }
            }
            binding.swipeRefresh.setRefreshing(false);
        });

        viewModel.getCobreListObserver().observe(this, serieIndicador -> {
            if (serieIndicador != null) {
                binding.nScroll.fechaCobreTextView.setText(viewModel.dateUtcToString(serieIndicador.get(0).getFecha()));
                binding.nScroll.valorCobreTextView.setText(viewModel.decimalFormat(serieIndicador.get(0).getValor()));
                if(viewModel.checkValueDecreased(serieIndicador.get(0).getValor(),serieIndicador.get(1).getValor())){
                    binding.nScroll.trendingCobre.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_trending_down));
                }
            }
            binding.swipeRefresh.setRefreshing(false);
        });

        viewModel.getDesempleoListObserver().observe(this, serieIndicador -> {
            if (serieIndicador != null) {
                binding.nScroll.fechaDesempleoTextView.setText(viewModel.dateUtcToString(serieIndicador.get(0).getFecha()));
                binding.nScroll.valorDesempleoTextView.setText(viewModel.decimalFormat(serieIndicador.get(0).getValor()));
                if(viewModel.checkValueDecreased(serieIndicador.get(0).getValor(),serieIndicador.get(1).getValor())){
                    binding.nScroll.trendingDesempleo.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_trending_down));
                }
            }
            binding.swipeRefresh.setRefreshing(false);
        });

        viewModel.getBitcoinListObserver().observe(this, serieIndicador -> {
            if (serieIndicador != null) {
                binding.nScroll.fechaBitcoinTextView.setText(viewModel.dateUtcToString(serieIndicador.get(0).getFecha()));
                binding.nScroll.valorBitcoinTextView.setText(viewModel.decimalFormat(serieIndicador.get(0).getValor()));
                if(viewModel.checkValueDecreased(serieIndicador.get(0).getValor(),serieIndicador.get(1).getValor())){
                    binding.nScroll.trendingBitcoin.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_trending_down));
                }
            }
            binding.swipeRefresh.setRefreshing(false);
        });

    }

    private void setUpView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout coll_toolbar = findViewById(R.id.collapsing_toolbar_layout);
        coll_toolbar.setTitle("Indicadores");
        coll_toolbar.setCollapsedTitleTextAppearance(R.style.coll_toolbar_title);
        coll_toolbar.setExpandedTitleTextAppearance(R.style.exp_toolbar_title);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);

        MobileAds.initialize(this, initializationStatus -> {
        });
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        binding.swipeRefresh.setOnRefreshListener(() -> viewModel.actualizarValores());
    }

    public void goToIndicador(View view) {
        String json = sharedPreferences.getDataByName((String) view.getTag());
        if (json == null || json.isEmpty()) {
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
                            sharedPreferences.setUIMode(LIGHTMODE);
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            bottomSheetDialog.dismiss();
                            break;

                        case R.id.rb_oscuro:
                            sharedPreferences.setUIMode(DARKMODE);
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            bottomSheetDialog.dismiss();
                            break;

                        case R.id.rb_sistema:
                            sharedPreferences.setUIMode(PREDETERMINADO);
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
        String modeOptionString = sharedPreferences.getDataByName(MODE_UI);
        RadioButton rb_claro = bottomSheetDialog.findViewById(R.id.rb_claro);
        RadioButton rb_oscuro = bottomSheetDialog.findViewById(R.id.rb_oscuro);
        RadioButton rb_sistema = bottomSheetDialog.findViewById(R.id.rb_sistema);

        if (Build.VERSION.SDK_INT < 29) {
            rb_sistema.setVisibility(View.GONE);
        }

        switch (modeOptionString) {
            case DARKMODE:
                rb_oscuro.setChecked(true);
                break;
            case LIGHTMODE:
                rb_claro.setChecked(true);
                break;
            case PREDETERMINADO:
                rb_sistema.setChecked(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.clearDisposable();
    }
}