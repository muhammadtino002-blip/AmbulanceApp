package com.example.ambulanceapp.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ambulanceapp.ui.theme.Montserrat

// Color tokens
private val NavyDark   = Color(0xFF1A2847)
private val NavyMid    = Color(0xFF3B5998)
private val FieldBg    = Color(0xFFF0F0F0)
private val HintGray   = Color(0xFF9E9E9E)
private val TextMain   = Color(0xFF1A1A1A)
private val TextSub    = Color(0xFF6B6B6B)
private val LinkColor  = NavyDark

// Screen
@Composable
fun SignInScreen(
    onSignInClick: (username: String, password: String) -> Unit = { _, _ -> },
    onForgotPasswordClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {}
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    fun handleSignIn() {
        errorMessage = when {
            username.isBlank() -> "Username tidak boleh kosong"
            password.isBlank() -> "Password tidak boleh kosong"
            password.length < 6 -> "Password minimal 6 karakter"
            else -> null
        }
        if (errorMessage == null) {
            onSignInClick(username, password)
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.weight(0.35f))

            // Title
            Text(
                text = "Sign In",
                fontSize = 26.sp,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Bold,
                color = TextMain
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Username field
            InputField(
                value = username,
                onValueChange = {
                    username = it
                    errorMessage = null
                },
                placeholder = "Username",
                leadingIcon = {
                    IconCircle {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Username icon",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                },
                keyboardType = KeyboardType.Text
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Password field
            InputField(
                value = password,
                onValueChange = {
                    password = it
                    errorMessage = null
                },
                placeholder = "Password",
                leadingIcon = {
                    IconCircle {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Password icon",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible)
                                Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = if (passwordVisible)
                                "Hide password" else "Show password",
                            tint = HintGray
                        )
                    }
                },
                visualTransformation = if (passwordVisible)
                    VisualTransformation.None else PasswordVisualTransformation(),
                keyboardType = KeyboardType.Password
            )

            // Pesan error
            if (errorMessage != null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = errorMessage ?: "",
                        color = Color(0xFFDC2626),
                        fontSize = 12.sp,
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Forgot Password
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                TextButton(onClick = onForgotPasswordClick) {
                    Text(
                        text = "Forgot Password",
                        color = LinkColor,
                        fontSize = 13.sp,
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Normal
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Sign In button
            Button(
                onClick = { handleSignIn() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = NavyDark)
            ) {
                Text(
                    text = "Sign In",
                    fontSize = 16.sp,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.weight(0.65f))

            // Sign Up footnote
            TextButton(onClick = onSignUpClick) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(
                            color = TextSub,
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Normal
                        )) {
                            append("Don't have an account? ")
                        }
                        withStyle(SpanStyle(
                            color = LinkColor,
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Bold
                        )) {
                            append("Sign Up")
                        }
                    },
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// Reusable: rounded input field
@Composable
private fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                color = HintGray,
                fontFamily = Montserrat,
                fontSize = 14.sp
            )
        },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        singleLine = true,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = FieldBg,
            unfocusedContainerColor = FieldBg,
            disabledContainerColor = FieldBg,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = NavyDark,
            focusedTextColor = TextMain,
            unfocusedTextColor = TextMain
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    )
}

// Reusable: colored circle that wraps an icon
@Composable
private fun IconCircle(content: @Composable () -> Unit) {
    Surface(
        shape = RoundedCornerShape(50),
        color = NavyMid,
        modifier = Modifier.size(34.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            content()
        }
    }
}

// Preview
@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun SignInScreenPreview() {
    MaterialTheme {
        SignInScreen()
    }
}