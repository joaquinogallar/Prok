setTimeout(function() {
    let message = document.getElementById("successMessage");
    if (message) {
        message.style.display = "none";
    }
}, 2000);

function closeMessage() {
    let message = document.getElementById("successMessage");
    if (message) {
        message.style.display = "none";
    }
}

let $openCreateTaskFormBtn = document.getElementById('open-create-class-form-btn');
let $closeCreateTaskFormBtn = document.getElementById('close-create-class-form-btn');
let $taskFrom = document.querySelector('.task-create-form');
let $bgFilter = document.querySelector('.bg-filter');

$openCreateTaskFormBtn.addEventListener("click", () => {
    $taskFrom.classList.remove('hidden');
    $bgFilter.classList.remove('hidden');
})

$closeCreateTaskFormBtn.addEventListener("click", () => {
    $taskFrom.classList.add('hidden');
    $bgFilter.classList.add('hidden');
})

const $openUpdateTaskFormBtns = document.querySelectorAll('[id^="update-"]');
const $closeUpdateTaskFromBtns = document.querySelectorAll('[id^="close-update-btn-"]');

$openUpdateTaskFormBtns.forEach((btn) => {
    btn.addEventListener("click", () => {
        const id = btn.id.split("update-")[1];
        const form = document.getElementById(`task-update-form-${id}`);

        if(form) {
            form.classList.remove('hidden');
        }

        if ($bgFilter) {
            $bgFilter.classList.remove('hidden');
        }
    })
})

$closeUpdateTaskFromBtns.forEach((btn) => {
    btn.addEventListener("click", () => {
        const id = btn.id.split("close-update-btn-")[1];
        const form = document.getElementById(`task-update-form-${id}`);

        if(form) {
            form.classList.add('hidden');
        }
        if ($bgFilter) {
            $bgFilter.classList.add('hidden');
        }
    })
})