package kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.authapi.auth.auth.AuthVm
import kh.com.vireak.developer.basic.portfolioui.ui.theme.Blue
import kh.com.vireak.developer.basic.portfolioui.ui.theme.CardBorder
import kh.com.vireak.developer.basic.portfolioui.ui.theme.NavyDark
import kh.com.vireak.developer.basic.portfolioui.ui.theme.PortfolioUiTheme
import kh.com.vireak.developer.basic.portfolioui.ui.theme.ScreenBg
import kh.com.vireak.developer.basic.portfolioui.ui.theme.Slate
import kh.com.vireak.developer.basic.portfolioui.ui.theme.SoftText
import kh.com.vireak.developer.basic.portfolioui.util.UiState

@Composable
fun LoginScreen(
    modifier : Modifier = Modifier,
    vm: AuthVm = viewModel(),
    onLoginSuccess: () -> Unit = {},
    onNavigateToRegister: () -> Unit = {}
){
    val state by vm.state.collectAsStateWithLifecycle()
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var showPassword by rememberSaveable{ mutableStateOf(false) }
    val isLoading = state is UiState.Loading
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(state) {
        if(state is UiState.Success){
            vm.resetState()
            onLoginSuccess()
        }
    }


    Scaffold(
        modifier = modifier, containerColor = ScreenBg
    ) {paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 24.dp)

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Text(
                        text = "Log in",
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold,
                        color = NavyDark
                    )
                    Text(text = "ចូលគណនី", fontSize = 16.sp, color = Slate)
                }
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .background(Color.White, CircleShape)
                        .border(1.dp, CardBorder, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "\$",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Blue
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .background(NavyDark, RoundedCornerShape(28.dp))
                    .height(190.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "Welcome Back · សូមស្វាគមន៍" ,
                    fontSize = 15.sp,
                    color = SoftText
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Your accounts,\nall in one place",
                    fontSize = 28.sp,
                    lineHeight = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = "Log in to see your balance and payments",
                    fontSize = 13.sp,
                    color = SoftText
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White, RoundedCornerShape(20.dp))
                    .border(1.dp, CardBorder, RoundedCornerShape(20.dp))
                    .padding(18.dp)
            ) {
                Text(
                    text = "EMAIL · អ៊ីមែល",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        emailError = null // clear the error when the user starts typing
                    },
                    placeholder = {Text(text = "example@gmail.com")},
                    singleLine = true,
                    enabled = !isLoading,
                    shape = RoundedCornerShape(14.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Blue,
                        unfocusedBorderColor = CardBorder,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    isError = emailError != null,
                    supportingText = { emailError?.let { Text(it) } },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Password · ពាក្យសំងាត់",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        passwordError = null // clear the error when the user starts typing
                    },
                    placeholder = {Text(text = "Enter your password")},
                    singleLine = true,
                    enabled = !isLoading,
                    shape = RoundedCornerShape(14.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Blue,
                        unfocusedBorderColor = CardBorder,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    visualTransformation = if (showPassword){
                        VisualTransformation.None
                    }else{
                        PasswordVisualTransformation()
                    },
                    trailingIcon = {
                        Text(
                            text = if (showPassword) "Hide" else "Show",
                            color = Blue,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(end = 14.dp)
                                .clickable { showPassword = !showPassword }
                        )
                    },
                    isError = passwordError != null,
                    supportingText = { passwordError?.let { Text(it) } },
                    modifier = Modifier.fillMaxWidth()
                )
                // Error from the API, for example "Wrong email or password"
                if (state is UiState.Error) {
                    Text(
                        text = (state as UiState.Error).message,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 8.dp),
                        fontSize = 13.sp
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        var valid = true
                        if ( email.isBlank()) {
                            emailError = "Please Enter Your Email"
                            valid = false
                        }else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()) {
                            emailError = "Enter a valid email"
                            valid = false
                        }
                        if (password.isBlank()){
                            passwordError = "Please Enter Your Password"
                            valid = false
                        }

                        if (valid) vm.login(email.trim(), password)
                        print("Logged in")
                    },
                        enabled = !isLoading,
                        shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = NavyDark,
                        contentColor = Color.White,
                        disabledContainerColor = NavyDark.copy(alpha = 0.5f),
                        disabledContentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    if (isLoading){
                        CircularProgressIndicator(
                            modifier = Modifier.size(22.dp),
                            strokeWidth = 2.dp,
                            color = Color.White
                        )
                    }else{
                        Text(
                            text = "Log in · ចូលគណនី",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                }

                Spacer(modifier = Modifier.height(18.dp))

                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Don't has an account?",
                        fontSize = 14.sp,
                        color = Slate
                    )
                    TextButton(
                        onClick = onNavigateToRegister,
                        enabled = !isLoading
                    ) {
                        Text(
                            text = "Sign up",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Blue
                        )
                    }

                }
            }
        }

    }

}


@Preview(showBackground = false)
@Composable
fun LoginScreenPreview(){
    PortfolioUiTheme() {
        LoginScreen()
    }
}