<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Verify OTP</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
* { margin:0; padding:0; box-sizing:border-box; }
body {
    min-height:100vh;
    background: linear-gradient(135deg, #0d0d1a, #2d0a4e, #0d0d1a);
    font-family:'Segoe UI', sans-serif;
}
.brand-logo img {
    width:70px; height:70px; border-radius:50%;
    object-fit:cover; border:2px solid #fff;
}
.navbar-container {
    height:100px; display:flex;
    justify-content:space-between; align-items:center; width:100%;
}
.navbar-brand { margin-left:25px; }
.bg-gradient-purple { background:linear-gradient(90deg,#9370DB,#4B0082); }

.form-wrapper {
    display:flex; align-items:center;
    justify-content:center; min-height:calc(100vh - 100px); padding:20px;
}
.card {
    border-radius:20px !important; overflow:hidden;
    box-shadow:0 8px 32px rgba(0,0,0,0.3);
    width:100%; max-width:460px;
}
.card-header-gradient {
    background:linear-gradient(90deg,#9370DB,#4B0082);
    color:white; font-weight:bold;
    font-size:1.25rem; text-align:center; padding:20px;
}
.card-body { background:#fff; padding:36px; }

.otp-input {
    letter-spacing:12px; font-size:1.6rem;
    text-align:center; border-radius:12px;
    border:2px solid #e0daf7; height:64px;
    width:100%; transition:border-color 0.2s; outline:none;
}
.otp-input:focus {
    border-color:#7A1FC0;
    box-shadow:0 0 0 3px rgba(122,31,192,0.12);
}
.otp-input.is-invalid { border-color:#dc3545 !important; }

.email-badge {
    background:#f5f0ff; border-radius:10px;
    padding:10px 16px; text-align:center;
    color:#5D0E99; font-weight:500;
    font-size:0.9rem; margin-bottom:16px; word-break:break-all;
}

/* ── Countdown timer box ── */
.timer-box {
    background:#fff8e1; border:1px solid #ffe082;
    border-radius:10px; padding:10px 16px;
    text-align:center; margin-bottom:20px;
    font-size:0.92rem; color:#795548;
    font-weight:500;
}
.timer-count {
    font-size:1.4rem; font-weight:700;
    color:#e53935; display:inline-block;
    min-width:36px;
}
.timer-expired {
    background:#fdecea; border:1px solid #f5c2c7;
    border-radius:10px; padding:10px 16px;
    text-align:center; margin-bottom:20px;
    font-size:0.92rem; color:#842029; font-weight:600;
}

/* ── Noscript fallback ── */
noscript .timer-box { display:none; }

.btn-verify {
    background:linear-gradient(45deg,#B19CD9,#7A1FC0);
    color:white; font-weight:bold; font-size:1.1rem;
    padding:13px; width:100%; border:none;
    border-radius:20px;
    box-shadow:0 4px 10px rgba(0,0,0,0.25);
    transition:all 0.3s ease; margin-top:8px; cursor:pointer;
}
.btn-verify:hover {
    background:linear-gradient(45deg,#A083D1,#5D0E99);
    transform:scale(1.02);
}
.btn-verify:disabled {
    background:#ccc; cursor:not-allowed; transform:none;
}

.links-row {
    display:flex; justify-content:space-between;
    margin-top:18px; flex-wrap:wrap; gap:8px;
}
.links-row a { color:#7A1FC0; text-decoration:none; font-size:0.85rem; }
.links-row a:hover { text-decoration:underline; }

.alert-success-custom {
    background:#d1e7dd; color:#0f5132;
    border:1px solid #badbcc; border-radius:10px;
    padding:12px 16px; margin-bottom:16px; font-size:0.9rem;
}
.alert-error-custom {
    background:#f8d7da; color:#842029;
    border:1px solid #f5c2c7; border-radius:10px;
    padding:12px 16px; margin-bottom:16px; font-size:0.9rem;
}
</style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar bg-gradient-purple">
  <div class="container-fluid navbar-container">
   <a class="navbar-brand" href="#">
         <img src="data:image/jpeg;base64,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"
              alt="X-Workz Logo" height="70" style="border-radius:50%; object-fit:cover;">
       </a>
  </div>
</nav>

<!-- OTP Form -->
<div class="form-wrapper">
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <div class="card">
          <div class="card-header-gradient">Verify OTP</div>
          <div class="card-body">

            <!-- Success message -->
            <c:if test="${not empty successMessage}">
              <div class="alert-success-custom" id="successBox">
                ${successMessage}
              </div>
            </c:if>

            <!-- Server-side OTP error (works with JS OFF too) -->
            <c:if test="${not empty otpError}">
              <div class="alert-error-custom">
                ${otpError}
              </div>
            </c:if>

            <!-- Email badge -->
            <div class="email-badge">
              OTP sent to: <strong>${emailId}</strong>
            </div>

            <!--
              COUNTDOWN TIMER
              - Visible when JS is ON  (shown by JS)
              - Hidden when JS is OFF  (noscript hides it via CSS)
              Server validates expiry anyway so JS-off users get
              "OTP expired" error from the server after submit.
            -->
            <div class="timer-box" id="timerBox" style="display:none;">
              OTP expires in <span class="timer-count" id="timerCount">20</span> seconds
            </div>

            <div class="timer-expired" id="expiredBox" style="display:none;">
               OTP has expired! Please request a new OTP.
            </div>

            <!-- Fallback for JS OFF: simple static message -->
            <noscript>
              <div class="alert-success-custom">
                 OTP is valid for 30 seconds from when it was sent.
              </div>
            </noscript>

            <!-- OTP Form -->
            <form action="verifyOtp" method="post"
                  id="otpForm" onsubmit="return validateOtpForm()">

              <input type="hidden" name="emailId" value="${emailId}">

              <div class="mb-4">
                <label class="form-label fw-semibold" style="color:#333;">
                  Enter 6-Digit OTP
                </label>
                <input type="text"
                       id="otpField"
                       name="otp"
                       class="otp-input"
                       maxlength="6"
                       placeholder="------"
                       autocomplete="one-time-code">
                <div class="text-danger small mt-2" id="otpClientError"></div>
              </div>

              <button type="submit" class="btn-verify" id="submitBtn">
                Verify OTP
              </button>
            </form>

            <div class="links-row">
              <a href="verifyEmail?emailId=${emailId}">Resend OTP</a>
              <a href="login">Back to Login</a>
            </div>

          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<script>
/* ─────────────────────────────────────────────
   COUNTDOWN TIMER — 20 seconds
   Works ONLY when JS is ON.
   Server-side expiry check handles JS-OFF case.
───────────────────────────────────────────── */
// Before
var EXPIRY_SECONDS = ${not empty otpExpirySeconds ? otpExpirySeconds : 30};
var timeLeft = EXPIRY_SECONDS;
var timerBox   = document.getElementById('timerBox');
var expiredBox = document.getElementById('expiredBox');
var timerCount = document.getElementById('timerCount');
var submitBtn  = document.getElementById('submitBtn');
var otpField   = document.getElementById('otpField');

// Show timer only when JS runs
timerBox.style.display = 'block';

var countdown = setInterval(function () {
    timeLeft--;
    timerCount.innerText = timeLeft;

    // Turn red in last 5 seconds
    if (timeLeft <= 5) {
        timerCount.style.color = '#b71c1c';
        timerBox.style.background = '#fdecea';
        timerBox.style.borderColor = '#f5c2c7';
    }

    if (timeLeft <= 0) {
        clearInterval(countdown);

        // Hide timer, show expired message
        timerBox.style.display    = 'none';
        expiredBox.style.display  = 'block';

        // Disable submit button and OTP field
        submitBtn.disabled      = true;
        submitBtn.innerText     = 'OTP Expired';
        otpField.disabled       = true;
        otpField.style.background = '#f8f8f8';
    }
}, 1000);

/* ── Form validation ── */
function validateOtpForm() {
    var otp     = document.getElementById('otpField');
    var errSpan = document.getElementById('otpClientError');

    if (timeLeft <= 0) {
        errSpan.innerText = 'OTP has expired. Please request a new one.';
        return false;
    }
    if (!otp.value.trim()) {
        otp.classList.add('is-invalid');
        errSpan.innerText = 'OTP cannot be empty';
        return false;
    }
    if (!/^\d{6}$/.test(otp.value.trim())) {
        otp.classList.add('is-invalid');
        errSpan.innerText = 'OTP must be exactly 6 digits';
        return false;
    }
    otp.classList.remove('is-invalid');
    errSpan.innerText = '';
    return true;
}

/* ── Live validation while typing ── */
otpField.addEventListener('input', function () {
    var err = document.getElementById('otpClientError');
    if (!this.value.trim()) {
        this.classList.add('is-invalid');
        err.innerText = 'OTP cannot be empty';
    } else if (!/^\d{6}$/.test(this.value.trim())) {
        this.classList.add('is-invalid');
        err.innerText = 'OTP must be exactly 6 digits';
    } else {
        this.classList.remove('is-invalid');
        err.innerText = '';
    }
});

/* ── Numbers only ── */
otpField.addEventListener('keypress', function (e) {
    if (!/[0-9]/.test(e.key)) e.preventDefault();
});

/* ── Auto-hide success alert after 4 seconds ── */
window.onload = function () {
    var success = document.getElementById('successBox');
    if (success) setTimeout(function () { success.style.display = 'none'; }, 4000);
};
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>