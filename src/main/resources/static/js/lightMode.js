document.addEventListener('DOMContentLoaded', () => {
  const editUserInfoBtn = document.getElementById('edit-user-info-btn');
  const configUserBtn = document.getElementById('config-user-btn');
  const formEditUserInfo = document.getElementById('form-edit-user-info');
  const userInfo = document.getElementById('userFullName');
  const closeCreateClassFormBtn = document.getElementById('close-create-class-form-btn');
  const themeToggleBtn = document.getElementById('toggle-theme');
  const body = document.body;

  if (editUserInfoBtn && configUserBtn && formEditUserInfo && userInfo && closeCreateClassFormBtn) {
    editUserInfoBtn.addEventListener('click', () => {
      userInfo.classList.add('hidden');
      formEditUserInfo.classList.remove('hidden');
      editUserInfoBtn.classList.add('hidden');
      configUserBtn.classList.add('hidden');
    });

    closeCreateClassFormBtn.addEventListener('click', () => {
      userInfo.classList.remove('hidden');
      formEditUserInfo.classList.add('hidden');
      editUserInfoBtn.classList.remove('hidden');
      configUserBtn.classList.remove('hidden');
    });
  }

  const storedTheme = localStorage.getItem('theme');
  if (storedTheme === 'light') {
    body.classList.add('light-mode');
    if (themeToggleBtn) themeToggleBtn.textContent = 'Dark Mode';
  }

  if (themeToggleBtn) {
    themeToggleBtn.addEventListener('click', () => {
      const isLight = body.classList.toggle('light-mode');
      localStorage.setItem('theme', isLight ? 'light' : 'dark');
      themeToggleBtn.textContent = isLight ? 'Dark Mode' : 'Light Mode';
    });
  }
});

