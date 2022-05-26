package br.com.viniciusmorici.appbeberagua

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import br.com.viniciusmorici.appbeberagua.model.CalcularIngestaoDiaria
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_categorias.*
import java.text.NumberFormat
import java.util.*

class categorias : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val context: Context get() = this
    private lateinit var calcularIngestao : CalcularIngestaoDiaria

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorias)

        //Retira e Esconde ToolBar
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //Chama as configurações de menu lateral
        configuraMenuLateral()

        val params = Bundle()

        var resultadoMl = 0.0 // var para o total de litros a ser tomado
        var addMl = Prefs.getString("lembrarTotal").toInt()// adiciona os litros

        calcularIngestao = CalcularIngestaoDiaria()
        // calcula os litros a serem tomados
        btnCalcular.setOnClickListener {
            if (editPeso.text.isEmpty()){
                Toast.makeText(context, "Informe o peso", Toast.LENGTH_SHORT).show()
            }
            if (editIdade.text.isEmpty()){
                Toast.makeText(context, "Informe a idade", Toast.LENGTH_SHORT).show()
            }else {
                val peso = editPeso.text.toString().toDouble()
                val idade = editIdade.text.toString().toInt()
                calcularIngestao.CalculatTotal(peso, idade)
                resultadoMl = calcularIngestao.ResultadoMl()
                val formartar = NumberFormat.getNumberInstance(Locale("pt", "BR"))
                formartar.isGroupingUsed = false
                txtResultado.text = "${formartar.format(resultadoMl)} L"
            }
        }

        //escolhe qual garrafa vai tomar
        btn500ml.setOnClickListener {
            addMl += 500
            Prefs.setString("lembrarTotal", addMl.toString())
            txtTotal.setText(addMl.toString())
        }

        btn700ml.setOnClickListener {
            addMl += 700
            Prefs.setString("lembrarTotal", addMl.toString())
            txtTotal.setText(addMl.toString())
        }
        btn1l.setOnClickListener {
            addMl += 1000
            Prefs.setString("lembrarTotal", addMl.toString())
            txtTotal.setText(addMl.toString())
        }
        btnReset.setOnClickListener {
            addMl = 0
            Prefs.setString("lembrarTotal", addMl.toString())
            txtTotal.setText(addMl.toString())
        }


        var lembrarTotalLitros = Prefs.getString("lembrarTotal")
        txtTotal.setText(lembrarTotalLitros)

        if (txtTotal.text.toString().toInt() >= 1000){
            txtMedidorTotal.text = "L"
        }

        //val args = intent.extras
        //val total = args?.getInt("totalCont", 0)
        //txtTotal.text = total.toString()

    }

    private fun configuraMenuLateral() {
// ícone de menu (hamburger) para mostrar o menu
        val toogle = ActionBarDrawerToggle(
            this,
            layoutMenuLateral,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close)
        layoutMenuLateral.addDrawerListener(toogle)
        toogle.syncState()
        menu_lateral.setNavigationItemSelectedListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_garrafas -> {
                Toast.makeText(context, "Esta já é a aba garrafas", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_sobre -> {
                Toast.makeText(context, "Este app é um app para beber água", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_sair -> {
                Toast.makeText(context, "Não saia, bebe agua", Toast.LENGTH_SHORT).show()
            }
        }
        layoutMenuLateral.closeDrawer(GravityCompat.START)
        return true
    }

}


