document.addEventListener("DOMContentLoaded", () => {
    const recordSelect = document.getElementById("record");
    const searchInput = document.getElementById("table-search");
    const paginationButtons = document.querySelectorAll(".btn-paginate");

    let currentPage = 1;
    let currentSize = 10;
    let currentQuery = "";

    const loadData = (page, size, query) => {
        const url = query ? `/role/search?query=${query}&page=${page}&size=${size}` : `/role/record?page=${page}&size=${size}`;

        fetch(url)
            .then(response => response.json())
            .then(data => {
                updateTable(data.roles, page);
                updatePagination(data.totalPages, page);
            });
    };

    const updateTable = (roles, page) => {
        const tableBody = document.getElementById("table-body");
        tableBody.innerHTML = "";

        roles.forEach((role, index) => {
            let permissionsHTML = '';
            role.namePermissions.forEach(permission => {
                console.log(permission);
                permissionsHTML += `
                    <div class="px-4 py-2 mr-4 mb-4 text-sm bg-green-500 text-white">
                        ${permission}
                    </div>
                `;
            })
            console.log(permissionsHTML);

            const row = `
                <tr>
                    <th>${index + 1 + (10*(page-1))}</th>
                    <th>${role.displayName}</th>
                    <th class="p-0 w-2/12"><div class="one-line-text">${role.description}</div></th>
                    <th><div class="flex flex-wrap">${permissionsHTML}</div></th>
                    <th class="action-class">
                        <a href="/role/update?id=${role.id}">
                            <i class="btn-action update fa-sharp fa-regular fa-pen-to-square mr-6"></i>
                        </a>
                    </th>
                </tr>
            `;
            tableBody.innerHTML += row;
        });
    };

    const updatePagination = (totalPages, currentPage) => {
        const paginationContainer = document.querySelector(".pagination .btn-group");
        const viewPage = document.querySelector(".pagination .view-page");
        paginationContainer.innerHTML = "";
        viewPage.innerHTML = '<h4 class="px-2">trang ' + currentPage + '/' + totalPages +'</h4>';

        const showPages = 5; // Số trang hiển thị trước và sau trang hiện tại
        const firstPage = 1;
        const lastPage = totalPages;

        // Nút "pre"
        const prevButton = document.createElement("button");
        prevButton.classList.add("btn", "btn-outline-info", "btn-paginate", "py-2");
        prevButton.innerHTML = '<i class="fa-regular fa-chevrons-left"></i>';
        prevButton.disabled = currentPage === 1; // Disable nút nếu đang ở trang đầu tiên
        prevButton.addEventListener("click", () => {
            loadData(currentPage - 1, currentSize, currentQuery);
        });
        paginationContainer.appendChild(prevButton);

        // Nút "1"
        if (currentPage > showPages) {
            const firstPageButton = document.createElement("button");
            firstPageButton.classList.add("btn", "btn-outline-info", "btn-paginate", "py-2");
            firstPageButton.textContent = firstPage;
            firstPageButton.addEventListener("click", () => {
                loadData(firstPage, currentSize, currentQuery);
            });
            paginationContainer.appendChild(firstPageButton);

            const dotsBefore = document.createElement("button");
            dotsBefore.classList.add("btn", "btn-outline-info", "btn-paginate", "py-2");
            dotsBefore.textContent = "...";
            dotsBefore.disabled = true;
            paginationContainer.appendChild(dotsBefore);
        }

        // Các trang quanh trang hiện tại
        for (let i = Math.max(currentPage - 2, firstPage); i <= Math.min(currentPage + 2, lastPage); i++) {
            const button = document.createElement("button");
            button.classList.add("btn", "btn-outline-info", "btn-paginate", "py-2");
            button.textContent = i;
            if (i === currentPage) button.classList.add("active");

            button.addEventListener("click", () => {
                loadData(i, currentSize, currentQuery);
            });
            paginationContainer.appendChild(button);
        }

        // Nút "..."
        if (currentPage < lastPage - showPages) {
            const dotsAfter = document.createElement("button");
            dotsAfter.classList.add("btn", "btn-outline-info", "btn-paginate", "py-2");
            dotsAfter.textContent = "...";
            dotsAfter.disabled = true;
            paginationContainer.appendChild(dotsAfter);

            const lastPageButton = document.createElement("button");
            lastPageButton.classList.add("btn", "btn-outline-info", "btn-paginate", "py-2");
            lastPageButton.textContent = lastPage;
            lastPageButton.addEventListener("click", () => {
                loadData(lastPage, currentSize, currentQuery);
            });
            paginationContainer.appendChild(lastPageButton);
        }

        // Nút "next"
        const nextButton = document.createElement("button");
        nextButton.classList.add("btn", "btn-outline-info", "btn-paginate", "py-2");
        nextButton.innerHTML = '<i class="fa-regular fa-chevrons-right"></i>';
        nextButton.disabled = currentPage === lastPage; // Disable nút nếu đang ở trang cuối cùng
        nextButton.addEventListener("click", () => {
            loadData(currentPage + 1, currentSize, currentQuery);
        });
        paginationContainer.appendChild(nextButton);
    };

    recordSelect.addEventListener("change", (e) => {
        currentSize = parseInt(e.target.value);
        loadData(currentPage, currentSize, currentQuery);
    });

    searchInput.addEventListener("input", (e) => {
        currentQuery = e.target.value;
        currentPage = 1;
        loadData(currentPage, currentSize, currentQuery);
    });

    paginationButtons.forEach(button => {
        button.addEventListener("click", (e) => {
            const direction = e.target.closest("button").dataset.direction;
            if (direction === "prev" && currentPage > 1) {
                currentPage--;
            } else if (direction === "next") {
                currentPage++;
            }
            loadData(currentPage, currentSize, currentQuery);
        });
    });

    // Initial load
    loadData(currentPage, currentSize, currentQuery);
});