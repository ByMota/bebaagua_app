package br.com.viniciusmorici.appbeberagua

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import br.com.viniciusmorici.appbeberagua.databinding.ActivityCadastroBinding
import br.com.viniciusmorici.appbeberagua.databinding.ActivityCategoriasBinding
import br.com.viniciusmorici.appbeberagua.model.CalcularIngestaoDiaria
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_categorias.*
import kotlinx.android.synthetic.main.header.*
import kotlinx.android.synthetic.main.toolbar.*
import java.text.NumberFormat
import java.util.*

class categorias : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val context: Context get() = this
    private lateinit var calcularIngestao: CalcularIngestaoDiaria
    private lateinit var binding: ActivityCategoriasBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()


        /* ToolBar*/
        setSupportActionBar(toolbar)

        //Retira e Esconde ToolBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        configuraMenuLateral()
        layoutMenuLateral.bringToFront()

        var addMl = Prefs.getString("lembrarTotal").toInt()// adiciona os litros

        calcularIngestao = CalcularIngestaoDiaria()
        // calcula os litros a serem tomados
        btnCalcular.setOnClickListener {
            if (editPeso.text.isEmpty()) {
                Toast.makeText(context, "Informe o peso", Toast.LENGTH_SHORT).show()
            }
            if (editIdade.text.isEmpty()) {
                Toast.makeText(context, "Informe a idade", Toast.LENGTH_SHORT).show()
            } else {
                val peso = editPeso.text.toString().toDouble()
                val idade = editIdade.text.toString().toInt()
                calcularIngestao.CalculatTotal(peso, idade)
                var resultadoMl = calcularIngestao.ResultadoMl()
                val formartar = NumberFormat.getNumberInstance(Locale("pt", "BR"))
                formartar.isGroupingUsed = false
                Prefs.setString(
                    "lembralNecessarioMl",
                    formartar.format(resultadoMl).toString()
                ) //salvo valor
                txtResultado.text = formartar.format(resultadoMl).toString()
            }
        }

        var lembrarNecessarioMl = Prefs.getString("lembralNecessarioMl")
        txtResultado.text = "${lembrarNecessarioMl.toString()} ML"

        //escolhe qual garrafa vai tomar
        btn500ml.setOnClickListener {
            addMl += 500
            Prefs.setString("lembrarTotal", addMl.toString())
            txtTotal.text = addMl.toString()
        }
        btn700ml.setOnClickListener {
            addMl += 700
            Prefs.setString("lembrarTotal", addMl.toString())
            txtTotal.text = addMl.toString()
        }
        btn1l.setOnClickListener {
            addMl += 1000
            Prefs.setString("lembrarTotal", addMl.toString())
            txtTotal.text = addMl.toString()
        }
        btnReset.setOnClickListener {
            addMl = 0
            Prefs.setString("lembrarTotal", addMl.toString())
            txtTotal.text = addMl.toString()
        }

        var lembrarTotalLitros = Prefs.getString("lembrarTotal")
        txtTotal.text = lembrarTotalLitros

    }

    private fun configuraMenuLateral() {
// ícone de menu (hamburger) para mostrar o menu

        val toogle = ActionBarDrawerToggle(
            this,
            layoutMenuLateral, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        layoutMenuLateral.addDrawerListener(toogle)
        toogle.syncState()
        nav_view.setNavigationItemSelectedListener(this)

    }

    // Deixa invisivel a barra de notificações
    override fun onBackPressed() {
        if (layoutMenuLateral.isDrawerOpen(GravityCompat.START)) {
            layoutMenuLateral.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_garrafas -> {
                Toast.makeText(context, "Esta já é a aba garrafas", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_sobre -> {
                val intent = Intent(this, conta::class.java)
                finish()
                startActivity(intent)
            }
            R.id.nav_sair -> {
                finish()
            }
        }
        layoutMenuLateral.closeDrawer(GravityCompat.START)
        return true
    }

}


