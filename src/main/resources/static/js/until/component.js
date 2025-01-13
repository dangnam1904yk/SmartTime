function GenerectUUID() {
    const list = ['A', 'b', 'c', '3', '6', 'D', 'e', '8', 'F', '9', 'T', '0'];
    const length = list.length;
    var random = "";
    for (let i = 0; i < 5; i++) {
        const randomIndex = Math.floor(Math.random() * length);
        random = random + (list[randomIndex]);
    }

    const date = new Date();
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0'); // Months are 0-based
    const day = String(date.getDate()).padStart(2, '0');
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    const seconds = String(date.getSeconds()).padStart(2, '0');
    const milliseconds = String(date.getMilliseconds()).padStart(3, '0'); // Milliseconds
    const formattedDate = `${year}${month}${day}${hours}${minutes}${seconds}${milliseconds}${random}`;
    return formattedDate
}