document.addEventListener("DOMContentLoaded", function () {
  function loadPonderersTable() {
    fetch("data/ponderers.json")
      .then((response) => response.json())
      .then((data) => {
        document.getElementById("main-content").innerHTML =
          '<div id="table-container"></div>';
        new Tabulator("#table-container", {
          data: data,
          layout: "fitColumns",
          height: "100%",
          pagination: "local",
          paginationSize: 50,
          paginationCounter: "rows",
          initialSort: [{ column: "challengesSolved", dir: "desc" }],
          columns: [
            {
              title: "#",
              field: "rowNum",
              width: 50,
              formatter: "rownum",
              headerSort: false,
            },
            {
              title: "Identifier",
              field: "identifier",
              sorter: "string",
              headerFilter: "input",
            },
            {
              title: "Challenges solved",
              field: "challengesSolved",
              sorter: "number",
              hozAlign: "right",
            },
            {
              title: "Bonus earned",
              field: "bonusEarned",
              sorter: "number",
              hozAlign: "right",
            },
            {
              title: "Challenges solved + bonus earned",
              field: "challengesSolvedPlusBonusEarned",
              sorter: "number",
              hozAlign: "right",
            },
          ],
        });
      })
      .catch((error) => console.error("Error loading JSON data:", error));
  }

  function loadChallengesTable() {
    fetch("data/challenges.json")
      .then((response) => response.json())
      .then((data) => {
        document.getElementById("main-content").innerHTML =
          '<div id="table-container"></div>';
        new Tabulator("#table-container", {
          data: data,
          layout: "fitColumns",
          height: "100%",
          pagination: "local",
          paginationSize: 50,
          paginationCounter: "rows",
          initialSort: [{ column: "correctSubmissionCount", dir: "desc" }],
          columns: [
            {
              title: "#",
              field: "rowNum",
              width: 50,
              formatter: "rownum",
              headerSort: false,
            },
            {
              title: "Identifier",
              field: "identifier",
              sorter: "string",
              headerFilter: "input",
            },
            {
              title: "Correct submission count",
              field: "correctSubmissionCount",
              sorter: "number",
              hozAlign: "right",
            },
            {
              title: "Bonus count",
              field: "bonusCount",
              sorter: "number",
              hozAlign: "right",
            },
            {
              title: "Bonus rate",
              field: "bonusRate",
              sorter: "number",
              hozAlign: "right",
            },
          ],
        });
      })
      .catch((error) => console.error("Error loading JSON data:", error));
  }

  function loadAboutPage() {
    const about = `<div class='container'>
          <div class='row'>
            <div class='col-md-8'>
              <p class='lead'>The statistics are compiled by
                 <a href="https://github.com/commandini" class="text-success fw-bold text-decoration-none" target="_blank">commandini</a> </p>
              <p>The statistics are computed up to and including <a href="https://research.ibm.com/haifa/ponderthis/challenges/July2025.html"><em>IBM Ponder This July 2025 Challenge</em></a>.</p>
            </div>
          </div>
        </div>`;
    document.getElementById("main-content").innerHTML = about;
  }

  document
    .getElementById("ponderer-statistics")
    .addEventListener("click", loadPonderersTable);
  document
    .getElementById("challenge-statistics")
    .addEventListener("click", loadChallengesTable);
  document.getElementById("about").addEventListener("click", loadAboutPage);
});
