package com.example.cartaofidelidade.Uteis;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cartaofidelidade.ConsultarAcitivty;
import com.example.cartaofidelidade.EditarActivity;
import com.example.cartaofidelidade.R;
import com.example.cartaofidelidade.model.PessoaActivity;
import com.example.cartaofidelidade.repository.PessoaRepository;
import java.util.ArrayList;
import java.util.List;

public class LinhaConsultarAdapter extends BaseAdapter {

    private static LayoutInflater layoutInflater = null;

    List<PessoaActivity> pessoaActivities = new ArrayList<PessoaActivity>();

    PessoaRepository pessoaRepository;

    private ConsultarAcitivty consultarAcitivty;

    public LinhaConsultarAdapter(ConsultarAcitivty consultarAcitivty, List<PessoaActivity>pessoaActivities){
        this.pessoaActivities = pessoaActivities;
        this.consultarAcitivty = consultarAcitivty;
        this.layoutInflater = (LayoutInflater)this.consultarAcitivty.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.pessoaRepository = new PessoaRepository(consultarAcitivty);
    }

    @Override
    public int getCount(){
        return pessoaActivities.size();
    }

    @Override
    public Object getItem(int position){
        return position;
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        final View viewLinhaLista = layoutInflater.inflate(R.layout.activity_linha_consultar,null);

        TextView textViewCodigo = (TextView)viewLinhaLista.findViewById(R.id.textViewId);
        TextView textViewNome = (TextView)viewLinhaLista.findViewById(R.id.textViewNome);
        TextView textViewCpf = (TextView)viewLinhaLista.findViewById(R.id.textViewCpf);
        TextView textViewNBt = (TextView)viewLinhaLista.findViewById(R.id.textViewEstrelas);

        Button buttonExcluir = (Button)viewLinhaLista.findViewById(R.id.buttonExcluir);
        Button buttonEditar = (Button)viewLinhaLista.findViewById(R.id.buttonEditar);

        textViewCodigo.setText(String.valueOf(pessoaActivities.get(position).getId()));
        textViewNome.setText(pessoaActivities.get(position).getNome());
        textViewCpf.setText(pessoaActivities.get(position).getCpf());
        textViewNBt.setText(String.valueOf(pessoaActivities.get(position).getNumeroBt()));


        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pessoaRepository.Excluir(pessoaActivities.get(position).getId());

                Toast.makeText(consultarAcitivty, "Registro excluido!",Toast.LENGTH_SHORT).show();
                AtualizarLista();
            }
        });

        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRedirecionar = new Intent(consultarAcitivty, EditarActivity.class);
                intentRedirecionar.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentRedirecionar.putExtra("id",pessoaActivities.get(position).getId());
                consultarAcitivty.startActivity(intentRedirecionar);
                consultarAcitivty.finish();
            }
        });

        return viewLinhaLista;
    }

    public void AtualizarLista(){

        this.pessoaActivities.clear();
        this.pessoaActivities = pessoaRepository.SelecionarTodos();
        this.notifyDataSetChanged();
    }
}
