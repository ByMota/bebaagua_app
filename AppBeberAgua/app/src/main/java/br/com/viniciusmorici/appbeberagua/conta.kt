package br.com.viniciusmorici.appbeberagua

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import br.com.viniciusmorici.appbeberagua.databinding.ActivityCadastroBinding
import br.com.viniciusmorici.appbeberagua.databinding.ActivityContaBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_categorias.*
import kotlinx.android.synthetic.main.activity_categorias.layoutMenuLateral
import kotlinx.android.synthetic.main.activity_categorias.nav_view
import kotlinx.android.synthetic.main.activity_conta.*
import kotlinx.android.synthetic.main.toolbar.*

class conta : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    private val context: Context get() = this
    private lateinit var binding: ActivityContaBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        txtNomeUser.text = firebaseAuth.currentUser?.email

        /*Configuração Toolbar*/
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        configuraMenuLateral()
        layoutMenuLateral.bringToFront()


        binding.txtDeletarConta.setOnClickListener {
            var user = firebaseAuth.currentUser
            user?.delete()
            finish()
        }

    }
    private fun configuraMenuLateral() {
// ícone de menu (hamburger) para mostrar o menu
        val toogle = ActionBarDrawerToggle(
            this,
            layoutMenuLateral,toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close)
        layoutMenuLateral.addDrawerListener(toogle)
        toogle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }

    // Deixa invisivel a barra de notificações
    override fun onBackPressed() {
        if(layoutMenuLateral.isDrawerOpen(GravityCompat.START)){
            layoutMenuLateral.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_garrafas -> {
                val intent = Intent(this, categorias::class.java)
                finish()
                startActivity(intent)
            }
            R.id.nav_sobre -> {
                Toast.makeText(context, "Esta já é a aba conta", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_sair -> {
                finish()
            }
        }
        layoutMenuLateral.closeDrawer(GravityCompat.START)
        return true
    }
}