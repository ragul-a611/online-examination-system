// Exam Timer Logic
function startExamTimer(remainingSeconds) {
    const timerElement = document.getElementById('exam-timer');
    const warningThreshold = 60; // 1 minute warning
    
    if (!timerElement) return;

    let time = remainingSeconds;

    const interval = setInterval(() => {
        if (time <= 0) {
            clearInterval(interval);
            timerElement.textContent = "00:00";
            alert("Time is up! Your examination will be submitted automatically.");
            document.getElementById("exam-form").action.value = 'submit';
            document.getElementById("exam-form").submit();
            return;
        }

        const minutes = Math.floor(time / 60);
        const seconds = time % 60;

        timerElement.textContent = 
            String(minutes).padStart(2, '0') + ":" + 
            String(seconds).padStart(2, '0');

        if (time <= warningThreshold) {
            timerElement.classList.add('timer-warning');
        }

        time--;
    }, 1000);
}

// Confirmation before manual submit
function confirmSubmission(event) {
    if(!confirm("Are you sure you want to submit the examination? You cannot change your answers later.")) {
        event.preventDefault();
        return false;
    }
    return true;
}

document.addEventListener("DOMContentLoaded", function() {
    // Check if there are password inputs for validation
    const registerForm = document.getElementById("registerForm");
    if(registerForm) {
        registerForm.addEventListener("submit", function(e) {
            const pwd = document.getElementById("password").value;
            const confirmPwd = document.getElementById("confirmPassword").value;
            
            if (pwd !== confirmPwd) {
                e.preventDefault();
                alert("Passwords do not match!");
            }
        });
    }
});
