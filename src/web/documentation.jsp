<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>
    <h1 class="page-header">API Dokumentation</h1>

    <table class="table">
        <tr>
            <td>
                <div>
                    <h4>Url</h4>
                    <code>/api/person/contactinfo</code>

                    <h4>HttpMethod</h4>
                    <kbd>GET</kbd>

                    <h4>Parameters</h4>
                    <kbd>{id} = int</kbd>
                </div>
            </td>
            <td>
                Udtrækker alle personers kontaktinfo.

                <h4>Response</h4>

                <table class="table">
                    <thead>
                        <tr>
                            <th>StatusCode</th>
                            <th>Output</th>
                        </tr>
                    </thead>
                    <tr>
                        <td>
                            <code>200</code>
                            Personer fundet
                        </td>
                        <td>
                            <pre>
[{
    id: 1,
    firstName: "Kim",
    lastName: "Larsen",
    email: "hahaha@ha.dk",
    phones: []
}, {
    id: 3,
    firstName: "Lukasz",
    lastName: "Mogensen",
    email: "hovhov@hov.dk",
    phones: []
}, {
    id: 4,
    firstName: "Brian",
    lastName: "Sandberg",
    email: "hejhej@hej.dk",
    phones: []
}]</pre>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <code>404</code>
                            Ingen personer fundet
                        </td>
                        <td>
                            <pre>
{
    code: 404,
    message: "No persons found"
}</pre>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <div>
                    <h4>Url</h4>
                    <code>/api/person/contactinfo/<kbd>{id}</kbd></code>

                    <h4>HttpMethod</h4>
                    <kbd>GET</kbd>

                    <h4>Parameters</h4>
                    <kbd>{id} = int</kbd>
                </div>
            </td>
            <td>
                Udtrækker personens kontoinfo ud fra id'et.

                <h4>Response</h4>

                <table class="table">
                    <thead>
                        <tr>
                            <th>StatusCode</th>
                            <th>Output</th>
                        </tr>
                    </thead>
                    <tr>
                        <td>
                            <code>200</code>
                            Person fundet
                        </td>
                        <td>
                            <pre>
{
    id: 1,
    firstName: "Kim",
    lastName: "Larsen",
    email: "hahaha@ha.dk",
    phones: []
}</pre>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <code>404</code>
                            Kunne ikke finde personen
                        </td>
                        <td>
                            <pre>
{
    code: 404,
    message: "Person with given id not found"
}</pre>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <div>
                    <h4>Url</h4>
                    <code>/api/person/complete/</code>

                    <h4>HttpMethod</h4>
                    <kbd>GET</kbd>

                    <h4>Parameters</h4>
                    <kbd>{id} = int</kbd>
                </div>
            </td>
            <td>
                Udtrækker alle personens oplysninger ud fra id'et.

                <h4>Response</h4>

                <table class="table">
                    <thead>
                        <tr>
                            <th>StatusCode</th>
                            <th>Output</th>
                        </tr>
                    </thead>
                    <tr>
                        <td>
                            <code>200</code>
                            Personer fundet
                        </td>
                        <td>
                            <pre>
[
  {
    "id": 1,
    "firstName": "Test",
    "lastName": "Test",
    "email": "test@test.dk",
    "hobbies": [],
    "phones": []
  },
  {
    "id": 2,
    "firstName": "Test",
    "lastName": "Test",
    "email": "test@test.dk",
    "hobbies": [],
    "phones": []
  },
  {
    "id": 3,
    "firstName": "Test",
    "lastName": "Test",
    "email": "test@test.dk",
    "hobbies": [
      {
        "id": 1,
        "name": "Fodbold",
        "description": "Fodbold..."
      }
    ],
    "phones": [
      {
        "id": 1,
        "number": 12345678,
        "description": "Home"
      }
    ]
  }
]</pre>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <code>404</code>
                            Kunne ikke finde nogle personer
                        </td>
                        <td>
                            <pre>
{
    code: 404,
    message: "No persons found"
}</pre>
                        </td>
                    </tr>
                </table>
                
            </td>
        </tr>
        <tr>
            <td>
                <div>
                    <h4>Url</h4>
                    <code>/api/person/complete/<kbd>{id}</kbd></code>

                    <h4>HttpMethod</h4>
                    <kbd>GET</kbd>

                    <h4>Parameters</h4>
                    <kbd>{id} = int</kbd>
                </div>
            </td>
            <td>
                Udtrækker alle personens oplysninger ud fra id'et.

                <h4>Response</h4>

                <table class="table">
                    <thead>
                        <tr>
                            <th>StatusCode</th>
                            <th>Output</th>
                        </tr>
                    </thead>
                    <tr>
                        <td>
                            <code>200</code>
                            Person fundet
                        </td>
                        <td>
                            <pre>
{
  "id": 3,
  "firstName": "Test",
  "lastName": "Test",
  "email": "test@test.dk",
  "hobbies": [
    {
      "id": 1,
      "name": "Fodbold",
      "description": "Fodbold..."
    }
  ],
  "phones": [
    {
      "id": 1,
      "number": 12345678,
      "description": "Home"
    }
  ]
}</pre>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <code>404</code>
                            Kunne ikke finde personen
                        </td>
                        <td>
                            <pre>
{
    code: 404,
    message: "Person with given id not found"
}</pre>
                        </td>
                    </tr>
                </table>

            </td>
        </tr>
        <tr>
            <td>
                <div>
                    <h4>Url</h4>
                    <code>/api/person/</code>

                    <h4>HttpMethod</h4>
                    <kbd>POST</kbd>

                    <h4>ContentType</h4>
                    <kbd>application/json</kbd>
                </div>
            </td>
            <td>
                Opretter en ny person

                <h3>Request</h3>
                <pre>
{
  "firstName": "Test",
  "lastName": "Test",
  "email": "test@test.dk",
  "hobbies": [
    {
      "id": 1,
      "name": "Fodbold",
      "description": "Fodbold..."
    }
  ],
  "phones": [
    {
      "id": 1,
      "number": 12345678,
      "description": "Home"
    }
  ],
    "address":
    {
        "street" : "Min vej",
        "additionalInfo" : "Dette er her jeg bor",
        "city" :
        {
            "city" : "Min by",
            "zip" : "1452"
        }
    }
}</pre>
                
                <h4>Response</h4>
                <table class="table">
                    <thead>
                        <tr>
                            <th>StatusCode</th>
                            <th>Output</th>
                        </tr>
                    </thead>
                    <tr>
                        <td>
                            <code>400</code>
                            Bad Request
                        </td>
                        <td>
                            Der mangler data
                        </td>
                    </tr>
                    <tr>
                        <td><code>201</code>
                            Created
                        </td>
                        <td>Personen er blevet oprettet</td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <div>
                    <h4>Url</h4>
                    <code>/api/person/</code>

                    <h4>HttpMethod</h4>
                    <kbd>PUT</kbd>

                    <h4>ContentType</h4>
                    <kbd>application/json</kbd>
                </div>
            </td>
            <td>
                Opdatere en person

                <h3>Request</h3>
                <pre>
{
  "id": 23,
  "firstName": "Test",
  "lastName": "Test",
  "email": "test@test.dk",
  "hobbies": [
    {
      "id": 1,
      "name": "Fodbold",
      "description": "Fodbold..."
    }
  ],
  "phones": [
    {
      "id": 1,
      "number": 12345678,
      "description": "Home"
    }
  ],
    "address":
    {
        "street" : "Min vej",
        "additionalInfo" : "Dette er her jeg bor",
        "city" :
        {
            "city" : "Min by",
            "zip" : "1452"
        }
    }
}</pre>
                <h4>Response</h4>
                <table class="table">
                    <thead>
                        <tr>
                            <th>StatusCode</th>
                            <th>Output</th>
                        </tr>
                    </thead>
                    <tr>
                        <td>
                            <code>400</code>
                            Bad Request
                        </td>
                        <td>
                            Der mangler data
                        </td>
                    </tr>
                    <tr>
                        <td><code>200</code>
                            OK
                        </td>
                        <td>Personen er blevet opdateret</td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <div>
                    <h4>Url</h4>
                    <code>/api/person/<kbd>{id}</kbd></code>

                    <h4>HttpMethod</h4>
                    <kbd>DELETE</kbd>

                    <h4>Parameters</h4>
                    <kbd>{id} = int</kbd>
                </div>
            </td>
            <td>
                Sletter en person
            
                <h4>Response</h4>
                <table class="table">
                    <thead>
                        <tr>
                            <th>StatusCode</th>
                            <th>Output</th>
                        </tr>
                    </thead>
                    <tr>
                        <td><code>200</code>
                            OK
                        </td>
                        <td>Personen er blevet slettet</td>
                    </tr>
                </table>
                    
            </td>
        </tr>
    </table>
</t:wrapper>