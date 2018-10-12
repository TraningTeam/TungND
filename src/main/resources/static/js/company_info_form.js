function addExistCompanyInfoForm(data) {
    var trParent = document.getElementById("exist_company");
    trParent.innerHTML = '';

    var tdEmpty = document.createElement("td");
    trParent.appendChild(tdEmpty);

    var table = document.createElement("table");

    var trCompanyName = document.createElement("tr");
    var tdCompanyName = document.createElement("td");
    var labelCompanyName = document.createElement("label");
    labelCompanyName.innerText = "Tên công ty";
    tdCompanyName.appendChild(labelCompanyName);
    trCompanyName.appendChild(tdCompanyName);
    var tdCompanyNameData = document.createElement("td");
    var labelCompanyNameData = document.createElement("label");
    labelCompanyNameData.innerText = data.companyName;
    tdCompanyNameData.appendChild(labelCompanyNameData);
    trCompanyName.appendChild(tdCompanyNameData);

    var trAddress = document.createElement("tr");
    var tdAddress = document.createElement("td");
    var labelAddress = document.createElement("label");
    labelAddress.innerText = "Địa chỉ";
    tdAddress.appendChild(labelAddress);
    trAddress.appendChild(tdAddress);
    var tdAddressData = document.createElement("td");
    var labelAddressData = document.createElement("label");
    labelAddressData.innerText = data.address;
    tdAddressData.appendChild(labelAddressData);
    trAddress.appendChild(tdAddressData);

    var trEmail = document.createElement("tr");
    var tdEmail = document.createElement("td");
    var labelEmail = document.createElement("label");
    labelEmail.innerText = "Email";
    tdEmail.appendChild(labelEmail);
    trEmail.appendChild(tdEmail);
    var tdEmailData = document.createElement("td");
    var labelEmailData = document.createElement("label");
    labelEmailData.innerText = data.email;
    tdEmailData.appendChild(labelEmailData);
    trEmail.appendChild(tdEmailData);

    var trTelephone = document.createElement("tr");
    var tdTelephone = document.createElement("td");
    var labelTelephone = document.createElement("label");
    labelTelephone.innerText = "Điện thoại";
    tdTelephone.appendChild(labelTelephone);
    trTelephone.appendChild(tdTelephone);
    var tdTelephoneData = document.createElement("td");
    var labelTelephoneData = document.createElement("label");
    labelTelephoneData.innerText = data.telephone;
    tdTelephoneData.appendChild(labelTelephoneData);
    trTelephone.appendChild(tdTelephoneData);

    table.appendChild(trCompanyName);
    table.appendChild(trAddress);
    table.appendChild(trEmail);
    table.appendChild(trTelephone);

    trParent.appendChild(table);
}