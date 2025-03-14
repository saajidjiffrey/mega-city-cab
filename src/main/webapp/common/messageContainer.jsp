<div class="toast-container  position-fixed top-0 end-0 p-3">
  <div id="messageToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
    <div class="toast-header">
      <span class="toast-icon rounded-circle bg-success p-2 me-3"></span>
      <strong class="me-auto">Bootstrap</strong>
      <small>11 mins ago</small>
      <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
    </div>
    <div class="toast-body">
      Hello, world! This is a toast message.
    </div>
  </div>
</div>
<script>
const toastTrigger = document.getElementById('liveToastBtn')
const toastLiveExample = document.getElementById('liveToast')

if (toastTrigger) {
  const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample)
  toastTrigger.addEventListener('click', () => {
    toastBootstrap.show()
  })
}

function showToast(message, iconClass = "bg-primary") {
    let toastBody = document.querySelector("#messageToast .toast-body");
    toastBody.textContent = message;

    let toastIcon = document.querySelector("#messageToast .toast-icon");
    if (toastIcon) {
        toastIcon.classList.remove("bg-success", "bg-danger", "bg-warning", "bg-info");
        toastIcon.classList.add(iconClass);
    }

    let toastHeaderStrong = document.querySelector("#messageToast .toast-header strong");
    toastHeaderStrong.textContent = "Notification";

    let toastHeaderSmall = document.querySelector("#messageToast .toast-header small");
    toastHeaderSmall.textContent = "Just now";

    let toastElement = document.getElementById("messageToast");
    let toastInstance = bootstrap.Toast.getOrCreateInstance(toastElement);
    toastInstance.show();
}
</script>