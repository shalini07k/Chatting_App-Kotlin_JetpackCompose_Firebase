package com.example.chatting_app.presentation.userregistrationscreen


import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.isPopupLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.chatting_app.R
import com.example.chatting_app.presentation.navigation.Routes
import com.example.chatting_app.presentation.viewmodel.AuthState
import com.example.chatting_app.presentation.viewmodel.PhoneAuthViewModel


@Composable
fun UserRegistrationScreen(navController: NavController, phoneAuthViewModel: PhoneAuthViewModel= hiltViewModel()){

    val authState by phoneAuthViewModel.authState.collectAsState()
    val context= LocalContext.current
    val activity = context as Activity

    var otp by remember { mutableStateOf("")}
    var verificationId by remember { mutableStateOf<String?>(null) }
    var expanded by remember{mutableStateOf(false)}
    var selectedCountry by remember { mutableStateOf("India") }
    var countryCode by remember { mutableStateOf("+91") }
    var phoneno by remember { mutableStateOf("") }

    Column(modifier=Modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier= Modifier.height(20.dp))

        Text(text = "Enter your phone number",
            fontSize = 20.sp,
            color = colorResource(R.color.light_green),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(25.dp))

        Row {
            Text(text= " WhatsApp will need to verify your phone number")

            Spacer(modifier = Modifier.width(4.dp))

            Text(text="what's",
                color = colorResource(R.color.dark_green)
            )

        }

        Text(text="my number",
            color = colorResource(R.color.dark_green)
        )

        Spacer(modifier= Modifier.height(16.dp))

        TextButton(onClick = {expanded= true},
            modifier = Modifier.fillMaxWidth()) {

            Box(modifier = Modifier.width(230.dp)){
                Text(text= selectedCountry,
                    modifier = Modifier.align(Alignment.Center) ,
                    fontSize = 16.sp,
                    color = Color.Black
                )

                Icon(imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterEnd),
                    tint= colorResource(R.color.light_green)
                )
            }
        }
        HorizontalDivider(modifier = Modifier.padding(horizontal = 66.dp),
            thickness = 2.dp,
            color = colorResource(R.color.light_green)
        )
        DropdownMenu(expanded =expanded, onDismissRequest = {expanded= false}, modifier=Modifier.fillMaxWidth()) {
            listOf("India", "USA", "Japan", "China", "Korea").forEach { country->
                DropdownMenuItem(text = {Text(text=country)},
                    onClick= {
                        selectedCountry=country
                        expanded= false
                    }
                )
            }
        }
        when(authState){
            is AuthState.Ideal, is AuthState.Loading, is AuthState.codesent->{
                if(authState is AuthState.codesent){
                    verificationId = (authState as AuthState.codesent).verificationId
                }
                if (verificationId == null){
                    Spacer(modifier= Modifier.height(16.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextField(
                            value = countryCode,
                            onValueChange = { countryCode = it },
                            modifier = Modifier.width(70.dp),
                            singleLine = true,
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = colorResource(R.color.light_green)
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))

                        TextField(
                            value= phoneno,
                            onValueChange = {phoneno= it},
                            placeholder = {Text(text= "Phone Number")},
                            singleLine = true,
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    Button(onClick = {
                        if(phoneno.isNotEmpty()){
                            val fullPhoneNumber ="$countryCode $phoneno"
                            phoneAuthViewModel.sendVerificationCode(fullPhoneNumber, activity)
                        }
                        else{
                            Toast.makeText(context, "Please Enter a valid phone number", Toast.LENGTH_SHORT).show()
                        }
                    },
                        shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.dark_green))) {
                        Text(text="Send OTP")
                    }
                    if (authState is AuthState.Loading){
                        Spacer(modifier = Modifier.height(16.dp))
                        CircularProgressIndicator()
                    }
                }else{
                    //otp UI
                    Spacer(modifier= Modifier.height(48.dp))
                    Text(
                        text= "Enter OTP",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.dark_green)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value= otp,
                        onValueChange = {otp= it},
                        placeholder = {Text(text= "OTP")},
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        )
                    )
                    Spacer(modifier = Modifier.height(32.dp))

                    Button(onClick = {
                        if (otp.isNotEmpty() && verificationId!= null){
                            phoneAuthViewModel.verifyCode(otp, context)
                        }
                        else{
                            Toast.makeText(context, "Please enter a valid OTP", Toast.LENGTH_SHORT).show()
                        }
                    }, shape= RoundedCornerShape(6.dp), colors = ButtonDefaults.buttonColors(colorResource(R.color.dark_green))
                        ) {
                        Text("Verify OTP")
                    }

                    if(authState is AuthState.Loading){
                        Spacer(modifier = Modifier.height(116.dp))
                        CircularProgressIndicator()
                    }

                }
            }
            is AuthState.success ->{
                Log.d("PhoneAuth","LoginSuccesful")
                phoneAuthViewModel.resetAuthState()

                navController.navigate(Routes.UserProfileScreen){
                    popUpTo<Routes.UserregistrationScreen>{
                        inclusive = true
                    }
                }

            }

            is AuthState.error->{
                Toast.makeText(context, (authState as AuthState.error).msg, Toast.LENGTH_SHORT).show()
            }
        }

//        Column(modifier = Modifier.fillMaxWidth().padding(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally) {
//
//            Row {
//                TextField(value= countryCode, onValueChange = {countryCode=it},
//                    modifier= Modifier.width(70.dp),
//                    singleLine = true,
//                    textStyle = LocalTextStyle.current.copy(fontSize = 15.sp),
//                    colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedIndicatorColor = colorResource(R.color.light_green),
//                        focusedIndicatorColor = colorResource(R.color.light_green)
//                    )
//                )
//
//                Spacer(modifier= Modifier.width(8.dp))
//
//                TextField(value = phoneno,
//                    onValueChange = { phoneno = it},
//                    placeholder = {Text(text = "Phone Number")},
//                    singleLine = true,
//                    colors = TextFieldDefaults.colors(
//                        unfocusedContainerColor = Color.Transparent,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedIndicatorColor = colorResource(R.color.light_green),
//                        focusedIndicatorColor = colorResource(R.color.light_green)
//                    )
//                )
//            }
//            Spacer( modifier = Modifier.height(16.dp))
//            Text(text= "Carrier charges may apply",
//                fontSize = 12.sp,
//                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
//            )
//            Spacer(modifier = Modifier.height(30.dp))
//            Button(onClick = {},
//                shape = RoundedCornerShape(6.dp),
//                colors= ButtonDefaults.buttonColors(containerColor = colorResource(R.color.dark_green)),
//                ) {
//                Text(text="Next", fontSize = 16.sp)
//            }
//        }

    }
}