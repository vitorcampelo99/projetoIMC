package br.com.aula.projeto04_caixatexto;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void calcular(View view) {
        TextInputEditText campoNome = findViewById(R.id.textNome);
        TextInputEditText campoPeso = findViewById(R.id.textPeso);
        TextInputEditText campoAltura = findViewById(R.id.textAltura);

        TextView resultado1 = findViewById(R.id.Resultado1);
        TextView resultado2 = findViewById(R.id.Resultado2);

        // Recupera os valores de entrada
        String nome = campoNome.getText().toString();
        String pesoStr = campoPeso.getText().toString();
        String alturaStr = campoAltura.getText().toString();

        // Inicializa as mensagens de erro
        String erro = "";
        String mensagemErro = "ENTRADA INVALIDA";

        try {
            // Valida se o peso e a altura são números válidos e positivos
            if (pesoStr.isEmpty() || alturaStr.isEmpty()) {
                erro = "ERRO";
            } else {
                Double numPeso = Double.parseDouble(pesoStr);

                // Verifica se a altura inclui um ponto decimal
                if (!alturaStr.contains(".") || alturaStr.length() < 4) {
                    erro = "ERRO";
                } else {
                    Double numAltura = Double.parseDouble(alturaStr);

                    if (numPeso <= 0 || numAltura <= 0) {
                        erro = "ERRO";
                    } else {
                        // Calcula o IMC
                        Double numImc = numPeso / (numAltura * numAltura);

                        // Define a classificação do IMC de acordo com a OMS
                        String classificacao;
                        if (numImc < 18.5) {
                            classificacao = "Baixo peso";
                        } else if (numImc >= 18.5 && numImc < 25) {
                            classificacao = "Peso normal";
                        } else if (numImc >= 25 && numImc < 30) {
                            classificacao = "Sobrepeso";
                        } else if (numImc >= 30 && numImc < 35) {
                            classificacao = "Obesidade Grau 1";
                        } else if (numImc >= 35 && numImc < 40) {
                            classificacao = "Obesidade Grau 2";
                        } else {
                            classificacao = "Obesidade Extrema (Grau 3)";
                        }

                        // Apresenta o resultado do IMC e a classificação
                        resultado1.setText(String.format("IMC: %.2f", numImc));
                        resultado2.setText(classificacao);
                        return; // Sai do método para evitar a exibição da mensagem de erro
                    }
                }
            }
        } catch (NumberFormatException e) {
            erro = "ERRO";
        }

        // Se houver um erro, apresenta a mensagem de erro
        if ("ERRO".equals(erro)) {
            resultado1.setText("ERRO");
            resultado2.setText(mensagemErro);
        }
    }

    public void limpar(View view) {
        TextView resultado1 = findViewById(R.id.Resultado1);
        TextView resultado2 = findViewById(R.id.Resultado2);

        // Limpa o texto de ambos os TextViews
        resultado1.setText("");
        resultado2.setText("");
    }



}