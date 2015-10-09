<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>
    <h1 class="page-header">Dokumentation</h1>

    <h2>API</h2>
    
    <table class="table">
        <thead>
            <tr>
                <th>URL</th>
                <th>Beskrivelse</th>
            </tr>
        </thead>
        <tr>
            <td>
                <code>/api/person/contactinfo</code>
            </td>
            <td>
                Udtrækker alle personers kontaktinfo.
                
                <h3>Response</h3>
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
                <code>/api/person/contactinfo/<kbd>{id}</kbd></code>
                <div>
                    <h4>Parameters</h4>
                    <kbd>{id} = int</kbd>
                </div>
            </td>
            <td>
                Udtrækker personens kontoinfo ud fra id'et.
                
                <h3>Response</h3>
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
                <code>/api/person/complete/</code>
            </td>
            <td>
                Udtrækker alle personens oplysninger ud fra id'et.
                
                <h3>Response</h3>
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
                <code>/api/person/complete/<kbd>{id}</kbd></code>
                <div>
                    <h4>Parameters</h4>
                    <kbd>{id} = int</kbd>
                </div>
            </td>
            <td>
                Udtrækker alle personens oplysninger ud fra id'et.
                
                <h3>Response</h3>
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
    </table>
</t:wrapper>