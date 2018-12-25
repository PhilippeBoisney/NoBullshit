<#import "template.ftl" as layout />

<@layout.mainLayout title="NoBullshit" description="Submit a job">

    <!-- HEADER --->
    <@layout.headerLayout currentUrl="/submit"/>

    <!-- CONTENT --->
    <@layout.contentLayout>
        <!-- Error message -->
        <#if error??>
            <div class="alert alert-danger" role="alert">${error}</div>
        </#if>

        <!-- Success message -->
        <#if success??>
            <div class="alert alert-success" role="alert">${success}</div>
        </#if>

        <!-- Form -->
        <div class="text-left">
            <form class="needs-validation" action="/submit" method="post" enctype="application/x-www-form-urlencoded" novalidate>

                <h4 class="mb-3">🔎 About the job</h4>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <!-- Job Title -->
                        <label for="jobTitle">Job Title</label>
                        <input type="text" class="form-control" id="jobTitle" placeholder="" name="jobTitle" required>
                        <div class="invalid-feedback">
                            Valid job title is required.
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <!-- Job URL -->
                        <label for="jobUrl">Job URL</label>
                        <input type="url" class="form-control" id="jobUrl" placeholder="" name="jobUrl" required>
                        <div class="invalid-feedback">
                            Valid job url is required.
                        </div>
                    </div>
                </div>

                <div class="row">

                    <!-- Category -->
                    <div class="col-md-6 mb-3">
                        <label for="jobCategory">Category</label>
                        <select class="custom-select d-block w-100" id="jobCategory" name="jobCategory" required>
                            <option value="1" selected>Android</option>
                        </select>
                        <div class="invalid-feedback">
                            Please select a valid category.
                        </div>
                    </div>

                    <!-- Type of Role -->
                    <div class="col-md-6 mb-3">
                        <label for="jobType">Type</label>
                        <select class="custom-select d-block w-100" id="jobType" name="jobType" required>
                            <option value="1" selected>Full Time</option>
                            <option value="2">Part-time</option>
                            <option value="3">Contract</option>
                            <option value="4">Intern</option>
                        </select>
                        <div class="invalid-feedback">
                            Please select a valid type of role.
                        </div>
                    </div>
                </div>

                <hr class="mb-4">

                <h4 class="mb-3">🚀 About the Company</h4>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <!-- Company Name -->
                        <label for="companyTitle">Company Title</label>
                        <input type="text" class="form-control" id="companyTitle" placeholder="" name="companyTitle" required>
                        <div class="invalid-feedback">
                            Valid company title is required.
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <!-- Company Logo -->
                        <label for="companyLogoUrl">Company Logo URL</label>
                        <input type="url" class="form-control" id="companyLogoUrl" placeholder="" name="companyLogoUrl" required>
                        <div class="invalid-feedback">
                            Valid company logo url is required.
                        </div>
                    </div>
                </div>

                <hr class="mb-4">

                <!-- SUBMIT -->
                <button class="btn btn-primary btn-lg btn-block" type="submit" value="Post">Submit</button>
            </form>
        </div>
    </@layout.contentLayout>

    <!-- FOOTER -->
    <@layout.footerLayout/>
</@layout.mainLayout>

<!-- JavaScript for disabling form submissions if there are invalid fields -->
<script>
  (function() {
    'use strict';

    window.addEventListener('load', function() {
      // Fetch all the forms we want to apply custom Bootstrap validation styles to
      var forms = document.getElementsByClassName('needs-validation');

      // Loop over them and prevent submission
      var validation = Array.prototype.filter.call(forms, function(form) {
        form.addEventListener('submit', function(event) {
          if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
          }
          form.classList.add('was-validated');
        }, false);
      });
    }, false);
  })();
</script>