#version 330 core

in vec3 N;
in vec3 ecPosition;
out vec4 color;

//uniform mat4 ModelView;
//uniform mat4 Projection;
uniform vec4 LightPosition;
uniform vec4 AmbientProduct, DiffuseProduct, SpecularProduct;
uniform float Shininess;

void main()
{
    // Here light position is defined in eye coordinates
    vec3 L = normalize( LightPosition.xyz - ecPosition );
    // If Light position is defined in world coordinates,
    // the next line is used instead of the above
    //vec3 L = normalize( (ModelView * (LightPosition-vPosition)).xyz);
    vec3 E = normalize( -ecPosition );
    vec3 H = normalize( L + E );


    // Compute terms in the illumination equation
    vec4 ambient = AmbientProduct;

    float Kd = max( dot(L, N), 0.0 );
    vec4  diffuse = Kd*DiffuseProduct;

    float Ks = pow( max(dot(N, H), 0.0), Shininess );
    vec4  specular = Ks * SpecularProduct;

    if( dot(L, N) < 0.0 ) {
        specular = vec4(0.0, 0.0, 0.0, 1.0);
    }

//    gl_Position = Projection * ModelView * vPosition;

    color = ambient + diffuse + specular;
    color.a = 1.0;
}
