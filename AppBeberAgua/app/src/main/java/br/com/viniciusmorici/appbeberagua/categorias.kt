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
        var idBtn = 0
        var resultadoMl = 0.0

        calcularIngestao = CalcularIngestaoDiaria()

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

        btn500ml.setOnClickListener {
            idBtn = 1
            val intent = Intent(this, contador::class.java)
            params.putString("500ml", "500ml")
            params.putInt("id_btn", idBtn)
            intent.putExtras(params)
            startActivity(intent)

        }
        btn700ml.setOnClickListener {
            idBtn = 2
            val intent = Intent(this, contador::class.java)
            params.putString("700ml", "700ml")
            params.putInt("id_btn", idBtn)
            intent.putExtras(params)
            startActivity(intent)

        }
        btn1l.setOnClickListener {
            idBtn = 3
            val intent = Intent(this, contador::class.java)
            params.putString("1l", "1Litro")
            params.putInt("id_btn", idBtn)
            intent.putExtras(params)
            startActivity(intent)

        }

        val args = intent.extras
        val total = args?.getInt("totalCont", 0)
        txtTotal.text = total.toString()



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


