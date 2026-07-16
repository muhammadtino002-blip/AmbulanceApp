package com.example.ambulanceapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ambulanceapp.ui.theme.Montserrat
import com.example.ambulanceapp.ui.theme.NavyPrimary
import com.example.ambulanceapp.ui.theme.NavySecondary
import com.example.ambulanceapp.ui.theme.NavyTertiary
import com.example.ambulanceapp.ui.theme.FieldBg
import com.example.ambulanceapp.ui.theme.HintGray
import com.example.ambulanceapp.ui.theme.TextPrimary
import com.example.ambulanceapp.ui.theme.TextSecondary

// Screen
@Composable
fun SignUpScreen(
    onBackClick: () -> Unit = {},
    onSignUpClick: (username: String, email: String, password: String) -> Unit = { _, _, _ -> },
) {
    var username        by remember { mutableStateOf("") }
    var email           by remember { mutableStateOf("") }
    var password        by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var termsAccepted   by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    fun handleSignUp() {
        errorMessage = when {
            username.isBlank() -> "Username tidak boleh kosong"
            email.isBlank() -> "Email tidak boleh kosong"
            password.isBlank() -> "Password tidak boleh kosong"
            password.length < 6 -> "Password minimal 6 karakter"
            else -> null
        }
        if (errorMessage == null) {
            onSignUpClick(username, email, password)
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {

            // Back arrow
            Spacer(modifier = Modifier.height(56.dp))
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Back",
                    tint = TextPrimary,
                    modifier = Modifier.size(20.dp)
                )
            }

            // Top spacer
            Spacer(modifier = Modifier.height(60.dp))

            // Title
            Text(
                text = "Sign Up",
                fontSize = 26.sp,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Username field
            SignUpInputField(
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
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            // E-mail field
            SignUpInputField(
                value = email,
                onValueChange = {
                    email = it
                    errorMessage = null
                                },
                placeholder = "E-mail",
                leadingIcon = {
                    IconCircle {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Email icon",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                },
                keyboardType = KeyboardType.Email
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Password field
            SignUpInputField(
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

            Spacer(modifier = Modifier.height(16.dp))

            // Terms & Conditions checkbox
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = termsAccepted,
                    onCheckedChange = { termsAccepted = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = NavyTertiary,
                        uncheckedColor = HintGray,
                        checkmarkColor = Color.White
                    ),
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "I agree to the terms & conditions",
                    fontSize = 13.sp,
                    fontFamily = Montserrat,
                    color = TextSecondary
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Sign Up button
            Button(
                onClick = { handleSignUp() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = NavySecondary)
            ) {
                Text(
                    text = "Sign Up",
                    fontSize = 16.sp,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

// Reusable: rounded input field
@Composable
private fun SignUpInputField(
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
            cursorColor = NavyPrimary,
            focusedTextColor = TextPrimary,
            unfocusedTextColor = TextPrimary
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
        color = NavyTertiary,
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
fun SignUpScreenPreview() {
    MaterialTheme {
        SignUpScreen()
    }
}