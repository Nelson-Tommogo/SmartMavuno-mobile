package com.example.smartmavuno.auth

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.window.DialogProperties

@Composable
fun LoadingDialog(isVisible: MutableState<Boolean>) {
    if (isVisible.value) {
        AlertDialog(
            onDismissRequest = { /* Do nothing */ },
            title = { Text("Creating Account") },
            text = { Text("Your account is being created. Please wait...") },
            confirmButton = {
                Button(onClick = { isVisible.value = false }) {
                    Text("OK")
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        )
    }
}

@Composable
fun SuccessDialog(isVisible: MutableState<Boolean>, onDismiss: () -> Unit) {
    if (isVisible.value) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Account Created") },
            text = { Text("Your account has been created successfully!") },
            confirmButton = {
                Button(onClick = onDismiss) {
                    Text("OK")
                }
            }
        )
    }
}

@Composable
fun LoginDialog(isVisible: MutableState<Boolean>, onDismiss: () -> Unit) {
    if (isVisible.value) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Logging In") },
            text = { Text("Your login request is being processed. Please wait...") },
            confirmButton = {
                Button(onClick = onDismiss) {
                    Text("OK")
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        )
    }
}

@Composable
fun LoginSuccessDialog(isVisible: MutableState<Boolean>, onDismiss: () -> Unit) {
    if (isVisible.value) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Login Successful") },
            text = { Text("You have logged in successfully!") },
            confirmButton = {
                Button(onClick = onDismiss) {
                    Text("OK")
                }
            }
        )
    }
}

@Composable
fun ResetPasswordDialog(isVisible: MutableState<Boolean>, onDismiss: () -> Unit) {
    if (isVisible.value) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Resetting Password") },
            text = { Text("Your password reset request is being processed. Please wait...") },
            confirmButton = {
                Button(onClick = onDismiss) {
                    Text("OK")
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        )
    }
}

@Composable
fun ResetPasswordSuccessDialog(isVisible: MutableState<Boolean>, onDismiss: () -> Unit) {
    if (isVisible.value) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Password Reset Successful") },
            text = { Text("Your password has been reset successfully! You can now log in with your new password.") },
            confirmButton = {
                Button(onClick = onDismiss) {
                    Text("OK")
                }
            }
        )
    }
}
