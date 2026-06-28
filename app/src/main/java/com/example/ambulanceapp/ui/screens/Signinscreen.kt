package com.example.ambulanceapp.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
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

// ── Color tokens ──────────────────────────────────────────────────────────────
private val NavyDark   = Color(0xFF1A2847)   // primary button & icon bg
private val NavyMid    = Color(0xFF3B5998)   // icon circle accent
private val FieldBg    = Color(0xFFF0F0F0)   // input field background
private val HintGray   = Color(0xFF9E9E9E)   // placeholder text
private val TextMain   = Color(0xFF1A1A1A)   // headings
private val TextSub    = Color(0xFF6B6B6B)   // secondary / footnote
private val LinkColor  = NavyDark            // "Forgot Password" & "Sign Up"

// ── Screen ────────────────────────────────────────────────────────────────────
@Composable
fun SignInScreen(
    onSignInClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

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

            // ── Top spacer (≈ 35 % of screen height) ──────────────────────
            Spacer(modifier = Modifier.weight(0.35f))

            // ── Title ──────────────────────────────────────────────────────
            Text(
                text = "Sign In",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = TextMain
            )

            Spacer(modifier = Modifier.height(32.dp))

            // ── E-mail field ───────────────────────────────────────────────
            InputField(
                value = email,
                onValueChange = { email = it },
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

            // ── Password field ─────────────────────────────────────────────
            InputField(
                value = password,
                onValueChange = { password = it },
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

            // ── Forgot Password ────────────────────────────────────────────
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                TextButton(onClick = onForgotPasswordClick) {
                    Text(
                        text = "Forgot Password",
                        color = LinkColor,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // ── Sign In button ─────────────────────────────────────────────
            Button(
                onClick = onSignInClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = NavyDark)
            ) {
                Text(
                    text = "Sign In",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            // ── Bottom spacer ──────────────────────────────────────────────
            Spacer(modifier = Modifier.weight(0.65f))

            // ── Sign Up footnote ───────────────────────────────────────────
            TextButton(onClick = onSignUpClick) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(color = TextSub, fontWeight = FontWeight.Normal)) {
                            append("Don't have an account? ")
                        }
                        withStyle(SpanStyle(color = LinkColor, fontWeight = FontWeight.Bold)) {
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

// ── Reusable: rounded input field ─────────────────────────────────────────────
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
            Text(text = placeholder, color = HintGray, fontSize = 14.sp)
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

// ── Reusable: colored circle that wraps an icon ────────────────────────────────
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

// ── Preview ────────────────────────────────────────────────────────────────────
@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun SignInScreenPreview() {
    MaterialTheme {
        SignInScreen()
    }
}