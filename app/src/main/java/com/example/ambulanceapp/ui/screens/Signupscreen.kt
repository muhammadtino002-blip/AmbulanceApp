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

// ── Color tokens (shared with SignInScreen) ────────────────────────────────────
private val NavyDark  = Color(0xFF1A2847)
private val NavyMid   = Color(0xFF3B5998)
private val FieldBg   = Color(0xFFF0F0F0)
private val HintGray  = Color(0xFF9E9E9E)
private val TextMain  = Color(0xFF1A1A1A)
private val TextSub   = Color(0xFF6B6B6B)
private val CheckBlue = Color(0xFF3B5998)

// ── Screen ─────────────────────────────────────────────────────────────────────
@Composable
fun SignUpScreen(
    onBackClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {}
) {
    var username        by remember { mutableStateOf("") }
    var email           by remember { mutableStateOf("") }
    var password        by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var termsAccepted   by remember { mutableStateOf(true) }   // checked by default (matches design)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {

            // ── Back arrow ─────────────────────────────────────────────────
            Spacer(modifier = Modifier.height(56.dp))
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Back",
                    tint = TextMain,
                    modifier = Modifier.size(20.dp)
                )
            }

            // ── Top spacer ─────────────────────────────────────────────────
            Spacer(modifier = Modifier.height(60.dp))

            // ── Title ──────────────────────────────────────────────────────
            Text(
                text = "Sign Up",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = TextMain,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // ── Username field ─────────────────────────────────────────────
            SignUpInputField(
                value = username,
                onValueChange = { username = it },
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

            // ── E-mail field ───────────────────────────────────────────────
            SignUpInputField(
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
            SignUpInputField(
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

            Spacer(modifier = Modifier.height(16.dp))

            // ── Terms & Conditions checkbox ────────────────────────────────
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = termsAccepted,
                    onCheckedChange = { termsAccepted = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = CheckBlue,
                        uncheckedColor = HintGray,
                        checkmarkColor = Color.White
                    ),
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "I agree to the terms & conditions",
                    fontSize = 13.sp,
                    color = TextSub
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // ── Sign Up button ─────────────────────────────────────────────
            Button(
                onClick = onSignUpClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = NavyDark)
            ) {
                Text(
                    text = "Sign Up",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

// ── Reusable: rounded input field ──────────────────────────────────────────────
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
fun SignUpScreenPreview() {
    MaterialTheme {
        SignUpScreen()
    }
}