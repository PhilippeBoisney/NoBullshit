<#import "template.ftl" as layout />

<@layout.mainLayout title="NoBullshit" description="Perfect jobs only">

            <!-- HEADER --->
            <@layout.headerLayout/>

            <!-- CONTENT --->
            <@layout.contentLayout>
                <h1>Perfect jobs only. No Bullshit.</h1>
                <p class="lead">
                    <small>
                        Each job submitted is reviewed by an expert developer.<br>
                        We publish only <strong>the best.</strong><br>
                    </small>
                </p>

                <p class="lead">
                    <a href="#" class="btn btn-primary btn-lg btn-xs">See the jobs</a>
                </p>
            </@layout.contentLayout>

            <!-- FOOTER -->
            <@layout.footerLayout/>
</@layout.mainLayout>