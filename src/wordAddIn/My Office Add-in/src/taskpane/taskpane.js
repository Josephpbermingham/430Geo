/*
 * Copyright (c) Microsoft Corporation. All rights reserved. Licensed under the MIT license.
 * See LICENSE in the project root for license information.
 */

/* global document, Office, Word */

var selectedText;


Office.onReady(info => 
{
	if (info.host === Office.HostType.Word) 
	{
		document.getElementById("sideload-msg").style.display = "none";
		document.getElementById("app-body").style.display = "flex";
		document.getElementById("create-tag").onclick = createTag;
		document.getElementById("tag-it").onclick = tagIt;
		
		Office.context.document.customXmlParts.getByNamespaceAsync("", function (result) 
		{
			//write("Number of parts found: " + result.value.length + "ID thing: " + result.value[0].id);
			if (result.value.length > 0) 
			{
				for(var i = 0; i < result.value.length; i++)
				{
					//write("Test2");
					var xmlPart = result.value[i];
					xmlPart.getNodesAsync('*/*', function (nodeResults) {
						//write("Node Results: " + nodeResults.value.length);
						//write(nodeResults.value[0].nodeType);
						for (var i = 0; i < nodeResults.value.length; i++) {
							var node = nodeResults.value[i];
							node.getNodeValueAsync(function (asyncResult) {
								//write(asyncResult.value);
								var newTagName = asyncResult.value.split("<TagName>")[1].split("</TagName>")[0];
								//write(newTagName);
								createTag2(newTagName);
							});
						}
					});
				}
			}
		});
	}
});

export async function createTag() {
  return Word.run(async context => {
    /**
     * Insert your Word code here
     */

    var newTagName = document.getElementById("new-tag").value;
	
	const xmlString = "<TagData xmlns=''><TagName>" + newTagName +  "</TagName></TagData>";
	
	Office.context.document.customXmlParts.addAsync(xmlString, (asyncResult) => 
	{
		$("#xml-id").text("Your new XML part's ID: " + asyncResult.value.id);
		asyncResult.value.getXmlAsync((asyncResult) => 
		{
			$("#xml-blob").text(asyncResult.value);
		});
	});
	
	// get reference to select element
	var sel = document.getElementById('tag');

	// create new option element
	var opt = document.createElement('option');

	// create text node to add to option element (opt)
	opt.appendChild( document.createTextNode(newTagName) );

	// set value property of opt
	opt.value = newTagName; 

	// add opt to end of select box (sel)
	sel.appendChild(opt); 
	//location.reload();
	
	write("Tag Created: " + newTagName);
	
    await context.sync();
  });
}

export async function createTag2(tagName) {
	return Word.run(async context => {
		var newTagName = tagName;
	
		// get reference to select element
		var sel = document.getElementById('tag');

		// create new option element
		var opt = document.createElement('option');

		// create text node to add to option element (opt)
		opt.appendChild( document.createTextNode(newTagName) );

		// set value property of opt
		opt.value = newTagName; 

		// add opt to end of select box (sel)
		sel.appendChild(opt); 
		//location.reload();
		await context.sync();
	});
}

export async function tagIt() {
	return Word.run(async context => {
		
		Office.context.document.getSelectedDataAsync(Office.CoercionType.Text, function (asyncResult) {
			if (asyncResult.status == Office.AsyncResultStatus.Failed) {
				write('Action failed. Error: ' + asyncResult.error.message);
			}
			else 
			{
				if(asyncResult.value != "")
				{
					//write(asyncResult.value + "\n\n---\n\n ");		
					//write("test");
					var e = document.getElementById("tag");
					var selectedTag = e.options[e.selectedIndex].text;
					
					if(selectedTag != "-Select a Tag-")
					{
						write("Tag '" + selectedTag + "' Applied to: " + asyncResult.value);
						
						const xmlString = "<TagData xmlns='NewVivo'><TagName>" + selectedTag + "</TagName><TaggedText>" + asyncResult.value + "</TaggedText></TagData>";
						
						//const xmlString = "<TagData xmlns=''><TagName>" + newTagName +  "</TagName></TagData>";
						
						Office.context.document.customXmlParts.addAsync(xmlString, (asyncResult) => 
						{
							$("#xml-id").text("Your new XML part's ID: " + asyncResult.value.id);
							asyncResult.value.getXmlAsync((asyncResult) => 
							{
								$("#xml-blob").text(asyncResult.value);
							});
						});
					}
					else
					{
						write("Please create/select a tag.");
					}
				}
				else
				{
					write("Please select some text within the Word document that you wish to tag.");
				}
			}
			
		});	
	});
}


// Function that writes to a div with id='output-msg' on the page.
function write(message){
    document.getElementById('output-msg').innerText = message; 
}

