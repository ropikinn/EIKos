package com.ropikin.eikos.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ropikin.eikos.R;
import com.ropikin.eikos.database.Constant;
import com.ropikin.eikos.database.SiswaDatabase;
import com.ropikin.eikos.model.SiswaModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListSiswaActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rvSiswa)
    RecyclerView rvSiswa;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.fab)
    FloatingActionButton fab;

    SiswaDatabase siswaDatabase;
    List<SiswaModel> siswaModelList;
    int id_kelas;
    Bundle bundle;
    private Context context1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_siswa);

        ButterKnife.bind(this);

        Toolbar tbDetailDokter = findViewById(R.id.toolbar);
        tbDetailDokter.setTitle("Daftar Siswa");
        setSupportActionBar(tbDetailDokter);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bundle = getIntent().getExtras();

        if (bundle != null) {
            id_kelas = bundle.getInt(Constant.KEY_ID_KELAS);
        }

        siswaDatabase = SiswaDatabase.createDatabase(this);

        siswaModelList = new ArrayList<>();
    }

    @Override
    protected void onResume() {
        super.onResume();

        siswaModelList.clear();

        getData();

       // rvSiswa.setLayoutManager(new LinearLayoutManager(this));
       // rvSiswa.setAdapter(new SiswaAdapter(this, siswaModelList, context1, siswaModelList1));
    }

    private void getData() {
        siswaModelList = siswaDatabase.kelasDao().selectSiswa(id_kelas);
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        startActivity(new Intent(this, com.ropikin.eikos.activities.TambahSiswaActivity.class).putExtra(Constant.KEY_ID_KELAS, id_kelas));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
