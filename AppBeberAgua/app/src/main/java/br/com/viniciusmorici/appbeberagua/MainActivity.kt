package br.com.viniciusmorici.appbeberagua
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.viniciusmorici.appbeberagua.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        if(firebaseAuth.currentUser != null){
            var intent = Intent(this, categorias::class.java)
            startActivity(intent)
        }

        binding.btnComecar.setOnClickListener {
            val email = binding.txtEmail.text.toString()
            val pass = binding.txtPass.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener{
                    if (it.isSuccessful){
                        var intent = Intent(this, categorias::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }

            }else{
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.txtCriarConta.setOnClickListener {
            var intent = Intent(this, Cadastro::class.java)
            startActivity(intent)
        }
        binding.txtChangePass.setOnClickListener {
            val email = binding.txtEmail.text.toString()
            if(email.isNotEmpty()){
                firebaseAuth.sendPasswordResetEmail(email).addOnSuccessListener{}
                Toast.makeText(this, "Um email com o link para redefinição de senha foi enviado para ${email}", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Preencha o campo do Email", Toast.LENGTH_SHORT).show()
            }
        }
    }
}